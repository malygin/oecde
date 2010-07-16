
package org.sgu.oecde.web.jsfbeans.util.fileUpload;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 17.08.2010
 */
public class UploadFile {

    private String fileName;
    private String contentType;
    private byte[] fileData;

    public UploadFile(String fileName, String contentType, byte[] fileData) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void getContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
