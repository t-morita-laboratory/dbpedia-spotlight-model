# DBpedia Spotlight Japanese 実行方法

## 必要データのダウンロード  

コードのダウンロード  
` $ git clone https://github.com/t-morita-laboratory/dbpedia-spotlight-model.git `
` $ cd dbpedia-spotlight-mode `
` $ mvn install ` 

  
日本語形態素解析器sudachiの辞書をダウンロード  
` $ wget http://sudachi.s3-website-ap-northeast-1.amazonaws.com/sudachidict/sudachi-dictionary-latest-core.zip `

  
ダウンロードしたsudachi辞書の解凍  
` $ tar -zxvf sudachi-dictionary-latest-core.zip `  

## 実行  

サーバの起動  
` $ mvn -pl rest exec:java -Dexec.mainClass=org.dbpedia.spotlight.web.rest.Server -Dexec.args="ja_sudachi/model http://0.0.0.0:2222/rest" `    


下記のURLにアクセス({text}の位置に文章を入力)  
http://localhost:2222/rest/annotate?text={text}
