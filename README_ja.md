# DBpedia Spotlight Japanese 実行方法

#環境
maven : 3.8  
lava : jdk1.8.0  

## 必要データのダウンロード  

コードのダウンロード  
` $ git clone https://github.com/t-morita-laboratory/dbpedia-spotlight-model.git `  
` $ cd dbpedia-spotlight-mode `   

  
日本語形態素解析器sudachiの辞書をダウンロード  
` $ wget http://sudachi.s3-website-ap-northeast-1.amazonaws.com/sudachidict/sudachi-dictionary-latest-core.zip `

  
ダウンロードしたsudachi辞書の解凍  
` $ unzip sudachi-dictionary-latest-core.zip `  
` $ cd sudachi-dictionary-XXXXXXXX `  
` $ mv system_core.dic ../ `  
` $ cd ../ `  

## 実行  

サーバの起動  
` $ mvn -pl rest exec:java -Dexec.mainClass=org.dbpedia.spotlight.web.rest.Server -Dexec.args="ja_sudachi/model http://0.0.0.0:2222/rest" `    


下記のURLにアクセス({text}の位置に文章を入力)  
http://localhost:2222/rest/annotate?text={text}

##モデルの作成
