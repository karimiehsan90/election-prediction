from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import ComplementNB
from flask import Flask, request
import numpy as np
import os
import json

dataset_base_dir = os.getenv('NLP_DATASET_BASEDIR', '/home/ehsan/datasets/emotion')
bind_ip = os.getenv('NLP_BIND_IP', '0.0.0.0')
port = int(os.getenv('NLP_PORT', '8000'))
training_contents = []
test_contents = []
training_tags = []
test_tags = []


def read_data(base_dir, contents, tags):
    for (dir_path, dir_names, file_names) in os.walk(base_dir):
        for file_name in file_names:
            with open('{}/{}'.format(dir_path, file_name), 'r') as file:
                lines = file.readlines()
                for line in lines:
                    line_items = line.split('\t')
                    tweet_content = line_items[1]
                    tweet_tag = line_items[2]
                    contents.append(tweet_content)
                    tags.append(tweet_tag)


read_data('{}/training'.format(dataset_base_dir), training_contents, training_tags)
read_data('{}/test'.format(dataset_base_dir), test_contents, test_tags)


contents_arr = np.array(training_contents)
tags_arr = np.array(training_tags)
vectorizer = CountVectorizer()
response = vectorizer.fit_transform(contents_arr)
naive_bayes = ComplementNB()
naive_bayes.fit(response, tags_arr)


report = {
    'fear': {
        'fear': 0,
        'joy': 0,
        'anger': 0,
        'sadness': 0,
    }, 'joy': {
        'fear': 0,
        'joy': 0,
        'anger': 0,
        'sadness': 0,
    }, 'anger': {
        'fear': 0,
        'joy': 0,
        'anger': 0,
        'sadness': 0,
    }, 'sadness': {
        'fear': 0,
        'joy': 0,
        'anger': 0,
        'sadness': 0,
    }
}


def predictor(contents, tags):
    np_arr = np.array(contents)
    resp = vectorizer.transform(np_arr)
    predict = naive_bayes.predict(resp)
    exact = 0
    for i in range(len(tags)):
        if tags[i] == predict[i]:
            exact += 1
        report[tags[i]][predict[i]] += 1
    return report, exact, len(tags)


rep, exact, all = predictor(test_contents, test_tags)
print((exact, all))
tag_ = ['fear', 'joy', 'anger', 'sadness']
o = ''
for t in tag_:
    o += t + ' '
o += '\n'
for t in tag_:
    for e in tag_:
        o += str(rep[t][e]) + ' '
    o += t + '\n'

print(o)

app = Flask(__name__)


@app.route('/', methods=['POST'])
def test_single():
    tweet_json = request.data.decode('utf-8', 'replace')
    tweet_dic = json.loads(tweet_json)
    tweet = tweet_dic['text']
    hashtags = ' '.join(tweet_dic['hashtags'])
    text = tweet + ' ' + hashtags
    np_arr = np.array([text])
    resp = vectorizer.transform(np_arr)
    predict = naive_bayes.predict(resp)
    return predict[0]


app.run(bind_ip, port)
