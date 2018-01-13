import numpy as np
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
        m = Y.shape[1]
        cost = -np.sum(np.multiply(Y, np.log(Al)) + np.multiply((1 - Y), np.log(1 - Al))) / m
        return cost

    def train_net(self, X, Y, X_max, itaration_steps=1000, learning_rate=0.1, print_cost=False):
        self.net_init(0.01)
        for i in range(itaration_steps + 1):
            # forward propagation
            self.foward_propagation(X, X_max)
            self.back_propagation(Y)
            self.update_parameters(learning_rate)
            if print_cost:
                if (i % 100 == 0):
                    cost = self.cal_cost(Y)
                    print("Iter" + str(i) + " Cost:", cost)

    def predict(self, X):
        A=self.foward_propagation(X,self.nomalize_max_num)
        predict_Y=(A>0.5)*1
        return predict_Y

    def accuracy(self, X, Y):
        pass

    def save_model(self):
        pass

    def load_model(self, path=None):
        if path == None:
            path = current_path
        pass


if __name__ == "__main__":
    ones = np.ones((10, 50))
    zeros = np.zeros((10, 50))
    X = np.column_stack((ones, zeros))
    ones = np.ones((1, 26))
    zeros = np.zeros((1, 74))
    Y = np.column_stack((ones, zeros))

    my_nn = NN_model([10, 5, 2, 1])
    my_nn.net_init(0.01)
    my_nn.train_net(X, Y, 1, 10000, 0.2, True)

    print(np.sum(my_nn.predict(X)))
