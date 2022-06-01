# DBpedia Spotlight Japanese  

## 必要コードのダウンロード  

` $ git clone https://github.com/t-morita-laboratory/dbpedia-spotlight-model.git   
 $ cd dbpedia-spotlight-mode && mvn install ` 

日本語形態素解析器sudachiの辞書をダウンロード  
``` $ wget http://sudachi.s3-website-ap-northeast-1.amazonaws.com/sudachidict/sudachi-dictionary-latest-core.zip ```

ダウンロードしたsudachi辞書の解凍  
``` $ tar -zxvf <ファイル名> ```  

## 実行  

``` mvn -pl rest exec:java -Dexec.mainClass=org.dbpedia.spotlight.web.rest.Server -Dexec.args="ja_sudachi/model http://0.0.0.0:2222/rest" ```    


http://localhost:2222/rest/annotate?text=<text>
