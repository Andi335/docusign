package com.forcont.digsigproto.common.controller.requests.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
public class DocumentUploadFormTest
{


    private List<MultipartFile> documents = new ArrayList<>();


    @Before
    public void generateDocuments() throws IOException
    {
        FileInputStream inputFile1 = new FileInputStream("src/test/java/com/forcont/digsigproto/common/controller/requests/model/Test1.pdf");
        MockMultipartFile file1 = new MockMultipartFile("file1", "Vertrag1", "multipart/form-data", inputFile1);

        FileInputStream inputFile2 = new FileInputStream("src/test/java/com/forcont/digsigproto/common/controller/requests/model/Test2.pdf");
        MockMultipartFile file2 = new MockMultipartFile("file2", "Vertrag2", "multipart/form-data", inputFile2);
        documents.add(file1);
        documents.add(file2);
    }
    @Test
    public void getDocument()
    {
        DocumentUploadForm documentUploadForm = new DocumentUploadForm(documents);
        assertThat(documentUploadForm.getDocument(), is(documents));
    }
}