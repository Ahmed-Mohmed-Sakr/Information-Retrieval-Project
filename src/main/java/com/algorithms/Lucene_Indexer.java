package com.algorithms;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Lucene_Indexer {

public static void Indexer() throws IOException{

    Directory indexDirct = FSDirectory.open(new File("indexDir"));
    boolean createNewIndex = true;
    IndexWriterConfig analyzerConfig =
            new IndexWriterConfig(Version.LUCENE_42, new SimpleAnalyzer(Version.LUCENE_42));
    analyzerConfig.setOpenMode(createNewIndex ? IndexWriterConfig.OpenMode.CREATE : IndexWriterConfig.OpenMode.APPEND);

    IndexWriter writer = new IndexWriter(indexDirct, analyzerConfig);
    String dataDir = "D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\Information-Retrieval-Project-Data-set\\build\\classes\\information\\retrieval\\project\\data\\set"; // Index *.txt files from this directory
    File[] files = new File(dataDir).listFiles();

    for (File f: files) {   // for each file in the directory
        if (!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead()){
            System.out.println("Indexing " + f.getCanonicalPath());
            Document doc = new Document();
            doc.add(new Field("content", new FileReader(f)));

            doc.add(new Field("filename", f.getName(),
                    Field.Store.YES, Field.Index.NOT_ANALYZED));

            doc.add(new Field("fullpath", f.getCanonicalPath(),
                    Field.Store.YES, Field.Index.NOT_ANALYZED));

            writer.addDocument(doc);
        }
    }
    System.out.println("# of Docs indexed = " + writer.numDocs());
    System.out.println("Lucene Index Built Successfully.");
    writer.close();
}
}
