from imports import *
from vgg16 import *
import h5py
def process_alldata_training():
    
    joint_transfer=[]
    frames_num=100
    count = 0
    
    with h5py.File('prueba.h5', 'r') as f:
            
        X_batch = f['data'][:]
        y_batch = f['labels'][:]

    for i in range(int(len(X_batch)/frames_num)):
        inc = count+frames_num
        joint_transfer.append([X_batch[count:inc],y_batch[count]])
        count =inc
        
    data =[]
    target=[]
    
    for i in joint_transfer:
        data.append(i[0])
        target.append(np.array(i[1]))
        
    return data, target

def process_alldata_test():
    
    joint_transfer=[]
    frames_num=100
    count = 0
    
    with h5py.File('pruebavalidation.h5', 'r') as f:
            
        X_batch = f['data'][:]
        y_batch = f['labels'][:]

    for i in range(int(len(X_batch)/frames_num)):
        inc = count+frames_num
        joint_transfer.append([X_batch[count:inc],y_batch[count]])
        count =inc
        
    data =[]
    target=[]
    
    for i in joint_transfer:
        data.append(i[0])
        target.append(np.array(i[1]))
        
    return data, target

data_test, target_test = process_alldata_test()
data, target = process_alldata_training()

chunk_size = 4096
n_chunks = 100
rnn_size = 512

model = Sequential()
model.add(LSTM(rnn_size, input_shape=(n_chunks, chunk_size)))
model.add(Dropout(0.5))
model.add(Dense(512))
model.add(Activation('relu'))
model.add(Dropout(0.5))
model.add(Dense(50))
model.add(Activation('sigmoid'))
model.add(Dropout(0.5))
model.add(Dense(2))
model.add(Activation('softmax'))
model.compile(loss='binary_crossentropy', optimizer='adam',metrics=['accuracy'])

epoch = 100
batchS = 500

history = model.fit(np.array(data[0:1200]), np.array(target[0:1200]), epochs=epoch,
                    validation_data=(np.array(data[1200:]), np.array(target[1200:])), 
                    batch_size=batchS, verbose=2)
model.save('model')

result = model.evaluate(np.array(data_test), np.array(target_test))

print(result)