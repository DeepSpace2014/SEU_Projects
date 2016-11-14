# -*- coding: utf-8 -*-

'''
@author: Lael
@date: 2016/11/14
@des:  随机森林的深入学习
@url:  http://mp.weixin.qq.com/s?__biz=MzI3MTA0MTk1MA==&mid=2651989467&idx=3&sn=0d23a165951faf10ce731568c6eaac3c
'''
import numpy as np
import pylab as plt

from sklearn.datasets import load_iris
from sklearn.ensemble import RandomForestClassifier
import pandas as pd
import numpy as np

'''
x = np.random.uniform(1,100,1000)
y = np.log(x) + np.random.normal(0,.3,1000)

pl.scatter(x, y, s=1, label='log(x) with noise')
pl.plot(np.arange(1,100), np.log(np.arange(1,100)), c='y', label='log(x) true function')
pl.xlabel('x')
pl.ylabel('f(x) = log(x)')
pl.legend(loc='best')
pl.title('A Basic Log Function')
plt.show()
'''


iris = load_iris()
print type(iris)
df = pd.DataFrame(iris.data, columns=iris.feature_names)
df['is_train'] = np.random.uniform(0, 1, len(df)) <= .75
df['species'] = pd.Categorical.from_codes(iris.target, iris.target_names)
print df.head()

train,test = df[df['is_train']==True], df[df['is_train'] == False]
features = df.columns[:4]
clf = RandomForestClassifier(n_jobs=2)
y, _ = pd.factorize(train['species'])
clf.fit(train[features],y)

preds = iris.target_names[clf.predict(test[features])]
print pd.crosstab(test['species'], preds, rownames=['actual'], colnames=['preds'])
print type(preds),preds