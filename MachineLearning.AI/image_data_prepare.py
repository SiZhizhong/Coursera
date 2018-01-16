from PIL import Image
import numpy as np
import os
from matplotlib import pyplot as plt
import pylab

# dogs
dog_dirs = os.listdir(r"D:\training image\dog")
tiger_dirs = os.listdir(r"D:\training image\tiger")

dog_datas = np.zeros((50 * 50, 1))

tiger_datas = np.zeros((50 * 50, 1))

for i in range(1000):
    dog = Image.open(r"D:\training image\dog" + "\\" + dog_dirs[i])  # type: Image.Image
    dog = dog.resize((50, 50))
    dog = dog.convert("L")
    # dog.save(r"D:\training image\resize\dog\dog"+str(i)+".jpg")
    dog_data = np.array(dog)
    dog_data = dog_data.reshape((50 * 50, 1))
    dog_datas = np.column_stack((dog_datas, dog_data))

for i in range(1000):
    tiger = Image.open(r"D:\training image\tiger" + "\\" + tiger_dirs[i])  # type: Image.Image
    tiger = tiger.resize((50, 50))
    tiger = tiger.convert("L")
    # tiger.save(r"D:\training image\resize\tiger\tiger" + str(i) + ".jpg")
    tiger_data = np.array(tiger)
    tiger_data = tiger_data.reshape((50 * 50, 1))
    tiger_datas = np.column_stack((tiger_datas, tiger_data))
dog_datas = dog_datas[:, 1:]
tiger_datas = tiger_datas[:, 1:]
print(dog_datas.shape)
print(tiger_datas.shape)
np.save("dogdata", dog_datas)
np.save("tigerdata", tiger_datas)
image = dog_datas[:, 1]  # type:np.ndarray
image = image.reshape(50, 50)
image = Image.fromarray(np.uint8(image))
image.show()
