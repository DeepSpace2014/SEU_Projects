# -*- coding: utf-8 -*-
"""
@date: 2016/11/14
@author: Lael
"""


import numpy as np
import pandas as pd
import time
import csv
from sklearn.grid_search import GridSearchCV
from sklearn.grid_search import RandomizedSearchCV
from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.ensemble import GradientBoostingRegressor
from sklearn.tree import ExtraTreeRegressor
from sklearn.linear_model import LinearRegression
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import make_scorer
from sklearn.externals import joblib
from sklearn.linear_model import LogisticRegression
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
from sklearn import cross_validation
from sklearn.cross_validation import KFold
from sklearn.linear_model import SGDRegressor
from sklearn.neighbors import KNeighborsRegressor
from sklearn.svm import SVR
from datetime import datetime
import math
import random

#  L(y,^y) = exp[abs( y - ^y)/y]
def obj3(labels,preds):
    #labels = dtrain.get_label()
    grad = np.arange(float(len(labels)))
    hess = np.arange(float(len(labels)))
    grad[labels ==0] = 0
    hess[labels==0] = 0
    for i in range(len(labels)):
        if labels[i]!=0:
            if labels[i] > preds[i]:
                grad[i] = float(-1)/labels[i] * math.exp(float(labels[i]-preds[i])/labels[i])
            else:
                grad[i] = float(1)/labels[i] * math.exp(float(labels[i]-preds[i])/labels[i])
            hess[i] = float(1)/math.pow(labels[i],2) * math.exp(float(labels[i]-preds[i])/labels[i])
    return grad , hess

def MAPE_score(y ,ypre):
    
    if abs(y-0.0)<0.00001:
        error = 0.0
    else:
        error = float(abs(y-ypre))/y
        
    return error

#labels ,preds is array or list like
def mape_loss(labels,preds):
    
    if len(labels)!=len(preds):
        print 'there is error, that prediction number  is not equal to samples'
    else:
        Sum = 0.0
        for i in range(len(labels)):
            Sum += MAPE_score(labels[i], preds[i])
        return float(Sum)/len(labels)
    


def LRprediction(train_X,train_y,test_X,valid_X, valid_y):
    tuned_parameters ={'penalty': 'l2', 'tol': 1e-4,\
                     'C': 1}
    lgr = LogisticRegression(**tuned_parameters)
    
    lgr.fit(train_X,train_y)
    result=lgr.predict(test_X)
    
    valid_ypred =  lgr.predict(valid_X)   
    
    valid_mape = mape_loss(valid_y,valid_ypred)
    
    print ' the mape score of LogitRregression in valid set is :' , valid_mape
    return result   
    

def ExtraTreeRegressorPrediction(train_X,train_y,test_X,valid_X, valid_y):
    etr = ExtraTreeRegressor()
    etr.fit(train_X,train_y)
    
    result=etr.predict(test_X)
    
    valid_ypred =  etr.predict(valid_X)   
    
    valid_mape = mape_loss(valid_y,valid_ypred)
    
    print ' the mape score of ExtraTreeRegressor in valid set is :' , valid_mape
    return result   
    
    
def LinearRegressionPredict(train_X,train_y,test_X,valid_X,valid_y):
    tuned_parameters ={'normalize': True }
   
    LR = LinearRegression(**tuned_parameters)
    
    LR.fit(train_X,train_y)
    result = LR.predict(test_X)
    
    valid_ypred =  LR.predict(valid_X)   
    
    valid_mape = mape_loss(valid_y,valid_ypred)
    
    print ' the mape score of LinearRegressor in valid set is :' , valid_mape
    return result  
 
def RandomForestPredict(train_X,train_y,test_X,valid_X, valid_y):
    
    tuned_parameters ={'n_estimators': 800 }
    
    rf = RandomForestRegressor(**tuned_parameters)
    
    rf.fit(train_X,train_y)
    result = rf.predict(test_X)
    
    valid_ypred =  rf.predict(valid_X)   
    
    valid_mape = mape_loss(valid_y,valid_ypred)
    
    print ' the mape score of RandomForest in valid set is :' , valid_mape
    return result  
    
def SGDRegressorPredict(train_X,train_y,test_X,valid_X, valid_y):
    
    sgd = SGDRegressor(loss = 'huber')   
    sgd.fit(train_X,train_y)
    result = sgd.predict(test_X)
    
    valid_ypred =  sgd.predict(valid_X)   
    
    valid_mape = mape_loss(valid_y,valid_ypred)
    
    print ' the mape score of SGDRegressor in valid set is :' , valid_mape
    return result  
 
def KNeighborsRegressorPredict(train_X,train_y,test_X,valid_X, valid_y):
    knr = KNeighborsRegressor(n_neighbors=20, weights='distance')
    
    knr.fit(train_X,train_y)
    result = knr.predict(test_X)
    
    valid_ypred =  knr.predict(valid_X)   
    
    valid_mape = mape_loss(valid_y,valid_ypred)
    
    print ' the mape score of KNeighbors in valid set is :' , valid_mape
    return result  

def SVRPredict(train_X,train_y,test_X,valid_X, valid_y):
    svr =SVR()
    svr.fit(train_X,train_y)
    result = svr.predict(test_X)
    
    valid_ypred =  svr.predict(valid_X)   
    
    valid_mape = mape_loss(valid_y,valid_ypred)
    
    print ' the mape score of SVR in valid set is :' , valid_mape
    return result  
    

# this is a class for stacking method
class Ensemble(object):
    def __init__(self, n_folds, stacker, base_models):
        self.n_folds = n_folds
        self.stacker = stacker
        self.base_models = base_models
        
    def fit_predict(self, X, y, valid_X,valid_y,T):
        X = np.array(X)
        y = np.array(y)
        T = np.array(T)
        valid_X = np.array(valid_X)
        valid_y = np.array(valid_y)

        folds = list(KFold(len(y), n_folds=self.n_folds, shuffle=True, random_state=2016))

        S_train = np.zeros((X.shape[0], len(self.base_models)))
        S_test = np.zeros((T.shape[0], len(self.base_models)))
        S_valid = np.zeros((valid_X.shape[0],len(self.base_models)))

        for i, clf in enumerate(self.base_models):
            print 'ensamble step:', i , datetime.now()
            S_test_i = np.zeros((T.shape[0], len(folds)))
            S_valid_i = np.zeros((valid_X.shape[0],len(folds)))

            for j, (train_idx, test_idx) in enumerate(folds):
                X_train = X[train_idx]
                y_train = y[train_idx]
                X_holdout = X[test_idx]
                # y_holdout = y[test_idx]
                clf.fit(X_train, y_train)
                y_pred = clf.predict(X_holdout)[:]
                S_train[test_idx, i] = y_pred
                S_test_i[:, j] = clf.predict(T)[:]
                S_valid_i[:,j] = clf.predict(valid_X)[:]
		mape = mape_loss(valid_y, S_valid_i)
		print 'mape score of model:' , mape , clf

            S_test[:, i] = S_test_i.mean(1)
            S_valid[:,i] = S_valid_i.mean(1)
            
        print S_test.shape , S_valid.shape
        
        self.stacker.fit(S_train, y)
        y_pred = self.stacker.predict(S_test)[:]
        
        joblib.dump(self.stacker,'stacker_gbrt.model')
        
        validy_pred = self.stacker.predict(S_valid)
        mape = mape_loss(valid_y,validy_pred)
        
        print 'the mape of layer-2 stacker is:' , mape
        return y_pred
   
    

    
if __name__=='__main__':
    
    train=pd.read_csv('train.csv',header=0)

    test=pd.read_csv('test.csv',header=0)

    
    train_X = train.values[:,5:-1]
    print train_X
    train_X = train_X.astype(float)
    print type(train_X),'------+++++'

    train_y = train['Future8'].values
    train_y = train_y.astype(float)

    test_X = test.values[:,5:]
    test_X = test_X.astype(float)
    
    print train_X.shape,test_X.shape
    print 'step1:' ,datetime.now()
    
      
    train_X, valid_X , train_y, valid_y = cross_validation.train_test_split(train_X, train_y,test_size=0.3,random_state=0)

    print 'step2:' ,datetime.now()
    result_lgr = LRprediction(train_X,train_y,test_X,valid_X, valid_y)

    print 'step3:' ,datetime.now()
    result_etr = ExtraTreeRegressorPrediction(train_X,train_y,test_X,valid_X, valid_y)
    print 'step4:' ,datetime.now()
    result_lr = LinearRegressionPredict(train_X,train_y,test_X,valid_X,valid_y)
    
    print 'step5:' ,datetime.now()
    result_rf = RandomForestPredict(train_X,train_y,test_X,valid_X, valid_y)
    print 'step6:' ,datetime.now()
    
    result_sgd = SGDRegressorPredict(train_X,train_y,test_X,valid_X, valid_y)
    print 'step7:' ,datetime.now()

    result_knr =KNeighborsRegressorPredict(train_X,train_y,test_X,valid_X, valid_y)
    print 'step8:' ,datetime.now()
    
    
    result_svr = SVRPredict(train_X,train_y,test_X,valid_X, valid_y)
    
    print 'step9:' ,datetime.now()
    
    submission = pd.DataFrame({'lgr':result_lgr,'etr':result_etr,'lr':result_lr,'rf':result_rf ,'sgd':result_sgd, 'knr':result_knr,'svr':result_svr}, columns=['pos','time','lgr','etr','lr','rf','sgd', 'knr','svr'])
    submission.to_csv("submission_ensamble.csv",index=False)

    
