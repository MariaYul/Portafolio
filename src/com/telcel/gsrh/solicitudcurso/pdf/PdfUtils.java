package com.telcel.gsrh.solicitudcurso.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BadPdfFormatException;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.telcel.gsrh.solicitudcurso.exception.PdfException;
import com.telcel.gsrh.solicitudcurso.utils.Server;

@Service
public class PdfUtils {
    
    @Autowired
    private Server server;
    
    private static final String FORMATO_ARCHIVO_IMAGEN = "jpg";
    private static final int TRESCIENTOS = 300;
    private static final String FORMATO_ARCHIVO_PDF = "pdf";
    public static final String SEPARADOR_ARCHIVOS_REPOSITORIO = "/";
    private static final String TERMINACION_ARCHIVO_IMAGEN = ".".concat(FORMATO_ARCHIVO_IMAGEN);
    public static final String TERMINACION_ARCHIVO_PDF = ".".concat(FORMATO_ARCHIVO_PDF);
    
    private static final String EXCEPCION_ARCHIVO_NO_ENCONTRADO = "Archivo no encontrado. ";
    private static final String EXCEPCION_ARCHIVO_TRATO = "Error en manipulación de archivo. ";
    
    private static final Logger LOGGER = Logger.getLogger(PdfUtils.class);
    
    @SuppressWarnings("unchecked")
    public List<String> convertirPdfToImagen(HttpServletRequest request, String nbArchivoDeployment) throws PdfException {
        List<String> coleccionImagenes = new ArrayList<String>();
        File archivo = getFileFromName(nbArchivoDeployment);
        InputStream input = null;
        PDDocument document = null;
        OutputStream output = null;
        BufferedImage imagen = null;
        ImageIO.scanForPlugins();
        
        try {
            input = getInputStreamFromFile(archivo);
            document = PDDocument.load(input, true);
            
            List<PDPage> paginas = document.getDocumentCatalog().getAllPages();
            if(paginas != null) {
                for(PDPage pagina : paginas) {
                    String nbArchivoImagen = server.getArchivoGenerado(request, TERMINACION_ARCHIVO_IMAGEN);
                    
                    coleccionImagenes.add(getRutaImagenContexto(nbArchivoImagen));
                    
                    imagen = pagina.convertToImage(BufferedImage.TYPE_INT_RGB, TRESCIENTOS);
                    output = new FileOutputStream(getRutaImagen(request, nbArchivoImagen));
                    ImageIO.write(imagen, FORMATO_ARCHIVO_IMAGEN, output);
                    
                    output.flush();
                    output.close();
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(EXCEPCION_ARCHIVO_NO_ENCONTRADO + e.getCause());
            throw new PdfException(EXCEPCION_ARCHIVO_NO_ENCONTRADO, e);
        } catch (IOException e) {
            LOGGER.error(EXCEPCION_ARCHIVO_TRATO + e.getCause());
            throw new PdfException(EXCEPCION_ARCHIVO_TRATO, e);
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(document);
            IOUtils.closeQuietly(input);
        }
        
        return coleccionImagenes;
    }
    
    public String unirArchivosPdf(HttpServletRequest request, List<String> rutas) throws PdfException {
        String nbArchivoPdf = server.getArchivoGenerado(request, TERMINACION_ARCHIVO_PDF);
        String rutaDestino = server.getResourceInDocumentsFolderPath(request, Server.DIRECTORIO_PDF, nbArchivoPdf);
        
        try {
            if(!rutas.isEmpty()) {
                leerYunirDocumentosPdf(request, rutas, rutaDestino);
            }
        } catch (DocumentException e) {
            LOGGER.error(EXCEPCION_ARCHIVO_TRATO + e.getCause());
            throw new PdfException(EXCEPCION_ARCHIVO_TRATO, e);
        } catch (FileNotFoundException e) {
            LOGGER.error(EXCEPCION_ARCHIVO_NO_ENCONTRADO + e.getCause());
            throw new PdfException(EXCEPCION_ARCHIVO_NO_ENCONTRADO, e);
        } catch (IOException e) {
            LOGGER.error(EXCEPCION_ARCHIVO_TRATO + e.getCause());
            throw new PdfException(EXCEPCION_ARCHIVO_TRATO, e);
        }
        
        return rutaDestino;
    }
    
    private void leerYunirDocumentosPdf(HttpServletRequest request, List<String> rutas, String rutaDestino) throws IOException, DocumentException {
        PdfCopy copy = null;
        Document document = new Document();
        
        try {
            copy = new PdfCopy(document, new FileOutputStream(rutaDestino));
            document.open();
            
            for(String ruta : rutas) {
                leerDocumento(request, copy, ruta);
            }
        } finally {
            document.close();
            
            if(copy != null) {
                copy.flush();
                copy.close();
            }
        }
    }
    
    private void leerDocumento(HttpServletRequest request, PdfCopy copy, String ruta) throws IOException, BadPdfFormatException {
        String rutaServidor = server.getResourceInDocumentsFolderPath(request, Server.DIRECTORIO_PDF, ruta);
        PdfReader reader = null;
        int n = 0;
        
        try {
            reader = new PdfReader(new RandomAccessFileOrArray(rutaServidor), null);
            n = reader.getNumberOfPages();
                
            if(!reader.isEncrypted()) {
                for (int i = 1; i <= n; i++) {
                    copy.addPage(copy.getImportedPage(reader, i));
                }
            }
        } finally {
            if(reader != null){
                reader.close();
            }
        }
    }
    
    private InputStream getInputStreamFromFile(File archivo) throws FileNotFoundException {
        return new FileInputStream(archivo);
    }
    
    private File getFileFromName(String nbArchivo) {
        return new File(nbArchivo);
    }
    
    private String getRutaImagen(HttpServletRequest request, String rutaImagen) {
        return server.getResourceInDocumentsFolderPath(request, Server.DIRECTORIO_IMAGES, rutaImagen);
    }
    
    private String getRutaImagenContexto(String nbArchivoImagen) {
        StringBuilder ruta = new StringBuilder();
        ruta.append(Server.DIRECTORIO_DOCUMENTOS);
        ruta.append(SEPARADOR_ARCHIVOS_REPOSITORIO);
        ruta.append(Server.DIRECTORIO_IMAGES);
        ruta.append(SEPARADOR_ARCHIVOS_REPOSITORIO);
        ruta.append(nbArchivoImagen);
        
        return ruta.toString();
    }
}
