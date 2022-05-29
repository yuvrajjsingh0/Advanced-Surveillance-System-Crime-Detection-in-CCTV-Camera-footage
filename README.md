# Advanced Surveillance System for Crime Detection in CCTV Camera footage

Detect Unlawful acts in videos using this AI based Surveillance System

### Here we're at checkpoint 2 of DTU Hackathon

In this, I've created the model that I would be training on videostreams collected from Youtube. I've created a Sequential Classifier with VGG16 to detect persons in the footage. It converts videos into 100 frames and then those 100 frames from each video is used for Pattern Recognition as we are using Sequential which is a RNN works as a predictive model where you have some sequence of inputs over space or time and the task is to predict a category for the sequence of events.

## TechStack
1. Tensorflow CPU Version
2. OpenCV
3. Firebase
4. Android (Java)

## Workflow:
1. AI detects any unlawful acts in a video
2. It sends the Alers to cloud(Firebase)
3. The devices connected to that firebase database get notification through the App. It can be the devices of Admin

## Social Impact

It can help in preventing major crimes from happening like Warehouse robbery, Killing, Kidnapping from Metro Stations. It can also be used by different online video streaming apps to monitor their content if they are doing that manually.

## How to run
Download trained model from this link: https://drive.google.com/drive/folders/1hphtqIc6qsFlyZ1t1ACTB6mqe83ijmtI?usp=sharing
Run: python inference.py
Login to Android app as yuvrajjsingh0@gmail.com password: yuvraj
Now you will get the alert if crime is detected

## Android App Screenshots
![Login](https://github.com/yuvrajjsingh0/Advanced-Surveillance-System-Crime-Detection-in-CCTV-Camera-footage/blob/checkpoint-3/Images/1.jpg)
![Alerts](https://github.com/yuvrajjsingh0/Advanced-Surveillance-System-Crime-Detection-in-CCTV-Camera-footage/blob/checkpoint-3/Images/2.jpg)
