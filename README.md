# DBpedia Spotlight Japanese  

## 必要コードのダウンロード  

```git clone https://github.com/t-morita-laboratory/dbpedia-spotlight-model.git```

日本語形態素解析器sudachi(http://sudachi.s3-website-ap-northeast-1.amazonaws.com/sudachidict/) の辞書をダウンロード  

ダウンロードしたファイルの解凍  
tar -zxvf <ファイル名>

system_core.dicをdbpedia-spotlight-modelディレクトリ直下に

入力 : ja_sudachi/model 
       http://0.0.0.0:80/rest

http://localhost/rest/annotate?text=テキスト
