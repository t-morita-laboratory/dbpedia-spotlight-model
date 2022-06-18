# 日本語DBpedia Spotlight実行方法

日本語DBpedia Spotlightでは、DBpediaを対象としたエンティティリンキングツールであるDBpediaに日本語形態素解析器Sudachiを導入し、日本語に特化したエンティティリンキングを行う  

作成済みモデルを使う場合は  **インストール** → **実行**  
モデル作成から行う場合は  **Wikipedia記事からの統計情報抽出** → **モデル構築**  

## 動作環境

maven : 3.1, or later  
Java Development Kit (JDK): JDK 1.8


## インストール  

dbpedia-spotlight-modelのソースコードのダウンロード、コンパイル、デプロイ  
` $ git clone https://github.com/t-morita-laboratory/dbpedia-spotlight-model.git && cd dbpedia-spotlight-model`  
` $ mvn install `   

日本語形態素解析器sudachiの辞書をダウンロード (core dictionaryを使用)  
` $ wget http://sudachi.s3-website-ap-northeast-1.amazonaws.com/sudachidict/sudachi-dictionary-latest-core.zip `
  
ダウンロードしたsudachi辞書の解凍  
` $ unzip sudachi-dictionary-latest-core.zip `  
` $ mv sudachi-dictionary-XXXXXXXX/system_core.dic ./ ` 

## 実行  

サーバの起動 (第一引数はモデルのディレクトリ、第二引数はサーバーURL)  
` $ mvn -pl rest exec:java -Dexec.mainClass=org.dbpedia.spotlight.web.rest.Server -Dexec.args="ja_sudachi/model http://0.0.0.0:2222/rest" `    

下記のURLにアクセス ({text}の位置に文章を入力)  
http://localhost:2222/rest/annotate?text={text}  

またはコマンドでの実行 ({text}にはパーセント(URL)エンコード(UTF-8)された文章を入力)  
` $ curl http://localhost:2222/rest/annotate?text={text} `  

出力例 (text=新幹線のぞみで人工知能学会全国大会にやってきた)  
```
$ curl http://localhost:2222/rest/annotate?text=%E6%96%B0%E5%B9%B9%E7%B7%9A%E3%81%AE%E3%81%9E%E3%81%BF%E3%81%A7%E4%BA%BA%E5%B7%A5%E7%9F%A5%E8%83%BD%E5%AD%A6%E4%BC%9A%E5%85%A8%E5%9B%BD%E5%A4%A7%E4%BC%9A%E3%81%AB%E3%82%84%E3%81%A3%E3%81%A6%E3%81%8D%E3%81%9F
{"@text":"新幹線のぞみで人工知能学会全国大会にやってきた","@confidence":"0.5","@support":"0","@types":"","@sparql":"","@policy":"whitelist","Resources":[{"@URI":"http://ja.dbpedia.org/resource/のぞみ_(列車)","@support":"1518","@types":"","@surfaceForm":"のぞみ","@offset":"3","@similarityScore":"0.9950609447494613","@percentageOfSecondRank":"0.0017892842055981617"},{"@URI":"http://ja.dbpedia.org/resource/人工知能学会","@support":"104","@types":"","@surfaceForm":"人工知能学会","@offset":"7","@similarityScore":"0.9999998190000974","@percentageOfSecondRank":"0.0"}]}
```  



## Wikipedia記事からの統計情報抽出

データのダウンロード、解凍  
` $ git clone https://github.com/dbpedia-spotlight/wikistatsextractor && mvn install`  
` $ git clone https://github.com/dbpedia-spotlight/model-quickstarter `  
` $ wget https://dumps.wikimedia.org/jawiki/latest/jawiki-latest-pages-articles.xml.bz2 ` 
` $ bzip2 -d jawiki-latest-pages-articles.xml.bz2 `  
` $ cd wikistatsextractor `  
` $ cp ../dbpedia-spotlight-model/system_core.dic ./ `  

統計情報抽出  
` $ mkdir ../dbpedia-spotlight-model/data `  
` $ mvn exec:java -Dexec.args="--output_folder ../dbpedia-spotlight-model/data ja ja_JP None ../jawiki-latest-pages-articles.xml ../model-quickstarter/ja/stopwords.list" `  


## モデル構築

DBpedia各種データのダウンロード、解凍  
` $ cd data `  
` $ wget https://downloads.dbpedia.org/2016-10/core-i18n/ja/disambiguations_ja.ttl.bz2 `  
` $ wget https://downloads.dbpedia.org/2016-10/core-i18n/ja/instance_types_ja.ttl.bz2 `  
` $ wget https://downloads.dbpedia.org/2016-10/core-i18n/ja/instance_types_transitive_ja.ttl.bz2 `  
` $ wget https://downloads.dbpedia.org/2016-10/core-i18n/ja/redirects_ja.ttl.bz2 `  
` $ bzcat disambiguations_ja.ttl.bz2 > disambiguations.nt `  
` $ bzcat instance_types_ja.ttl.bz2 > instance_types.tmp `  
` $ bzcat instance_types_transitive_ja.ttl.bz2 > instance_types_transitive.tmp `  
` $ bzcat redirects_ja.ttl.bz2 > redirects.nt `  
` $ cat instance_types_transitive.tmp >> instance_types.tmp && cat instance_types.tmp | sort > instance_types.nt `  

モデルの構築  
` $ cd ../ `  
` $ mvn exec:java -Dexec.args="--output_folder ja_JP dbdata/ model/ None ../model-quickstarter/ja/stopwords.list None" `  


## Licenses  

All the original code produced for DBpedia Spotlight Model is licensed under Apache License, 2.0.
