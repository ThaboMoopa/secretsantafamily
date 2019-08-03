package com.secretsanta;

import org.junit.Test;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.InputStream;

public class TestPDF {

    @Test
    public void testPdf()
    {
        InputStream stream = this.getClass().getResourceAsStream("myInvoice.pdf");
        //InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("myInvoice.pdf");

        StreamedContent file = new DefaultStreamedContent(stream, "myInvoice.pdf", "myInvoice.pdf");

        System.out.println(file.getName());

    }


}
