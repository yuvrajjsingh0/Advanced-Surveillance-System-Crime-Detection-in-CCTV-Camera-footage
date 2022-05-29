from re import U
from imports import *
from vgg16 import *

import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

import time

cred = credentials.Certificate('serviceAccount.json')
firebase_admin.initialize_app(cred)

db = firestore.client()

def get_frame(current_dir, file_name):
    
    in_file = os.path.join(current_dir, file_name)
    images = []
    
    vidcap = cv2.VideoCapture(in_file)
    
    success,image = vidcap.read()
        
    count = 0

    while count<images_per_file:
                
        RGB_img = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    
        res = cv2.resize(RGB_img, dsize=(img_size, img_size),
                                 interpolation=cv2.INTER_CUBIC)
    
        images.append(res)
    
        success,image = vidcap.read()
    
        count += 1
        
    resul = np.array(images)
    
    resul = (resul / 255.).astype(np.float16)
        
    return resul
model111= keras.models.load_model('model')

shape = (images_per_file,) + img_size_touple + (3,)

image_batch = np.zeros(shape=shape, dtype=np.float32)
    
image_batch = get_frame('./TESTING/', 'fi101.avi') # change video accordingly
# Note that we use 16-bit floating-points to save memory.
shape = (images_per_file, transfer_values_size)
transfer_values = np.zeros(shape=shape, dtype=np.float16)
        
transfer_values = image_model_transfer.predict(image_batch)

joint_transfer=[]
frames_num=100
count = 0

for i in range(int(len(transfer_values)/frames_num)):
    inc = count+frames_num
    joint_transfer.append([transfer_values[count:inc]])
    count =inc
        
data =[]
    
for i in joint_transfer:
    data.append(i[0])

prediction = model111.predict(np.array(data))

if prediction[0][0] > 0.89:
    dat = {
        u'Location': u'Los Angeles',
        u'Accuracy': prediction[0][0] * 100,
        u'date': time.time()
    }
    db.collection(u'crimes').add(data)