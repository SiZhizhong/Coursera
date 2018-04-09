import numpy as np
import pickle
from PIL import Image

from matplotlib import pyplot as plt


class NN_model(object):
    # net dimensions: An array contains the each layer dimension,first item is X feature number
    net_dims = None
    layer_num = None
    # parameters: contain the W,b of each layer
    parameters = dict()
    # caches: a dict contain Z and A of each layer
    caches = dict()
    grad_caches = dict()
    nomalize_max_num = None

    def __init__(self, net_dims):
        self.net_dims = np.array(net_dims)
        self.layer_num = self.net_dims.shape[0] - 1  # substract the input layer

    def set_netdims(self, net_dims):
        self.net_dims = np.array(net_dims)
        self.layer_num = self.net_dims.shape[0] - 1  # substract the input layer

    # initialize the parameters
    def net_init(self, init_ratio=0.01):
        if self.net_dims == None:
            print("Initialize the Net size first")
            return
        else:
            for i in range(self.layer_num):
                self.parameters["W" + str(i + 1)] = init_ratio * np.random.randn(self.net_dims[i + 1], self.net_dims[i])
                self.parameters["b" + str(i + 1)] = np.zeros((self.net_dims[i + 1], 1))

    def sigmoid(self, Z):
        activation = 1 / (1 + np.exp(-Z))
        return activation

    def foward_propagation(self, X, nomalize_max_num=255):
        # normalize X
        self.nomalize_max_num = nomalize_max_num
        A = np.divide(np.array(X), nomalize_max_num)
        self.caches["A0"] = np.array(A)
        # for each layer, calculate activation and cache Z and A
        for i in range(self.layer_num - 1):
            W = self.parameters["W" + str(i + 1)]
            b = self.parameters["b" + str(i + 1)]
            Z = np.dot(W, A) + b
            A = np.tanh(Z)
            self.caches["Z" + str(i + 1)] = Z
            self.caches["A" + str(i + 1)] = A
        # output layer
        W = self.parameters["W" + str(self.layer_num)]
        b = self.parameters["b" + str(self.layer_num)]
        Z = np.dot(W, A) + b
        A = self.sigmoid(Z)
        self.caches["Z" + str(self.layer_num)] = Z
        self.caches["A" + str(self.layer_num)] = A
        return A

    def back_propagation(self, Y):
        # the output layer
        m = Y.shape[1]
        # n-1 layer w
        A = self.caches["A" + str(self.layer_num)]
        A_1 = self.caches["A" + str(self.layer_num - 1)]
        dZ = A - Y
        dW = np.dot(dZ, A_1.T) / m
        db = np.sum(dZ, axis=1, keepdims=True) / m
        self.grad_caches["dZ" + str(self.layer_num)] = dZ
        self.grad_caches["dW" + str(self.layer_num)] = dW
        self.grad_caches["db" + str(self.layer_num)] = db
        for i in range(self.layer_num - 1, 0, -1):
            W_next_layer = self.parameters["W" + str(i + 1)]
            dZ_next_layer = self.grad_caches["dZ" + str(i + 1)]
            A = self.caches["A" + str(i)]
            A_1 = self.caches["A" + str(i - 1)]
            dZ = np.multiply(np.dot(W_next_layer.T, dZ_next_layer), (1 - A ** 2))
            dW = np.dot(dZ, A_1.T) / m
            db = np.sum(dZ, axis=1, keepdims=True) / m
            self.grad_caches["dZ" + str(i)] = dZ
            self.grad_caches["dW" + str(i)] = dW
            self.grad_caches["db" + str(i)] = db

    def update_parameters(self, learning_rate=0.1):
        for i in range(self.layer_num):
            self.parameters["W" + str(i + 1)] = self.parameters["W" + str(i + 1)] - learning_rate * self.grad_caches[
                "dW" + str(i + 1)]
            self.parameters["b" + str(i + 1)] = self.parameters["b" + str(i + 1)] - learning_rate * self.grad_caches[
                "db" + str(i + 1)]

    def cal_cost(self, Y):
        Al = self.caches["A" + str(self.layer_num)]
        n = Y.shape[0]
        m = Y.shape[1]
        cost = -np.sum(np.multiply(Y, np.log(Al)) + np.multiply((1 - Y), np.log(1 - Al))) / (m * n)
        return cost

    def train_net(self, X, Y, X_max, itaration_steps=1000, learning_rate=0.1, print_cost=False):
        #self.net_init(0.01)
        for i in range(itaration_steps + 1):
            # forward propagation
            self.foward_propagation(X, X_max)
            self.back_propagation(Y)
            self.update_parameters(learning_rate)
            if print_cost:
                if (i % 10 == 0):
                    cost = self.cal_cost(Y)
                    print("Iter" + str(i) + " Cost:", cost)

    def predict(self, X):
        A = self.foward_propagation(X, self.nomalize_max_num)
        predict_Y = (A > 0.5) * 1
        return predict_Y

    def accuracy(self, X, Y):
        predict_Y = self.predict(X)
        m = np.array(Y).shape[1]
        correct = np.all(predict_Y == Y, axis=0, keepdims=True) * 1
        accuracy = correct.sum() / m
        return accuracy

    def save_model(self,model_name):
        save_data = dict()
        save_data["net_dims"] = self.net_dims
        save_data["layer_num"] = self.layer_num
        save_data["caches"] = self.caches
        save_data["grad_caches"] = self.grad_caches
        save_data["nomalize_max_num"] = self.nomalize_max_num
        save_data["parameters"] = self.parameters
        file = open(model_name, "wb")
        pickle.dump(save_data, file)

    def load_model(self, model_name):
        file = open(model_name, "rb")
        save_data = pickle.load(file)
        self.net_dims = save_data["net_dims"]
        self.layer_num = save_data["layer_num"]
        self.caches = save_data["caches"]
        self.grad_caches = save_data["grad_caches"]
        self.nomalize_max_num = save_data["nomalize_max_num"]
        self.parameters = save_data["parameters"]



if __name__ == "__main__":
    dogs=np.load("dogdata.npy")
    tigers=np.load("tigerdata.npy")
    dogs_train=dogs[:,:600]
    dogs_test=dogs[:,600:]
    tigers_train=tigers[:,:600]
    tigers_test=tigers[:,600:]

    #dog is one
    train_X=np.column_stack((dogs_train,tigers_train))
    train_Y=np.column_stack((np.ones((1,dogs_train.shape[1])),np.zeros((1,tigers_train.shape[1]))))

    test_X=np.column_stack((dogs_test,tigers_test))
    test_Y=np.column_stack((np.ones((1,dogs_test.shape[1])),np.zeros((1,tigers_test.shape[1]))))

    print(train_X.shape,train_Y.shape,test_X.shape,test_Y.shape)
    my_nn = NN_model([50*50, 500, 10, 1])
    my_nn.load_model("isdog")
    my_nn.train_net(train_X,train_Y,255,700,0.02,True)
    my_nn.save_model("isdog")
    print(my_nn.accuracy(train_X,train_Y),my_nn.accuracy(test_X,test_Y))
