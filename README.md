# Advanced Surveillance System for Crime Detection in CCTV Camera footage

Detect Unlawful acts in videos using this AI based Surveillance System

### Here we're at checkpoint 2 of DTU Hackathon

In this, I've created the model that I would be training on videostreams collected from Youtube. I've created a Sequential Classifier with VGG16 to detect persons in the footage. It converts videos into 100 frames and then those 100 frames from each video is used for Pattern Recognition as we are using Sequential which is a RNN works as a predictive model where you have some sequence of inputs over space or time and the task is to predict a category for the sequence of events.


