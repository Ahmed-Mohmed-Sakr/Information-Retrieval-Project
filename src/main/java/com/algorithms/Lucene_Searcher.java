package com.algorithms;
import java.io.File;
import java.io.IOException;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
public class Lucene_Searcher {

    public static int searcher(String str_query) throws IOException, ParseException {
        Directory indexDirct = FSDirectory.open(new File("indexDir"));
        IndexReader reader = DirectoryReader.open(indexDirct);
        IndexSearcher is = new IndexSearcher(reader);

        QueryParser parser = new QueryParser(Version.LUCENE_42,
                "content",
                new SimpleAnalyzer(Version.LUCENE_42));

        Query query = parser.parse(str_query);

        TopDocs hits = is.search(query, 10);

        System.out.println("Found " + hits.totalHits +
                " document(s) that matched query '" + str_query + "':");

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            System.out.println(doc.get("fullpath"));
        }

        reader.close();
        return  hits.totalHits;
    }
}
