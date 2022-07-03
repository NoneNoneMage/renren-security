package io.renren.modules.oss.cloud;

import io.renren.common.config.ApiProperties;
import io.renren.common.exception.RRException;
import io.renren.common.exception.RRExceptionHandler;
import io.renren.common.utils.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/8 <br>
 * @see io.renren.modules.oss.cloud <br>
 * @since R9.0<br>
 */
public class DiskStorageService extends CloudStorageService {
    private static final Logger logger = LoggerFactory.getLogger(DiskStorageService.class);

    public DiskStorageService(CloudStorageConfig config) {
        this.config = config;
    }

    @Override
    public String upload(byte[] data, String path) {
        checkPath(path);
        File file = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data);
        }
        catch (FileNotFoundException e) {
            logger.warn(e.getMessage());
            return null;
        }
        catch (IOException e) {
            logger.warn(e.getMessage());
            return null;
        }
        finally {
            try {
                if (null != fos) {
                    fos.flush();
                    fos.close();
                }
            }
            catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

        return getOuterPath(path);
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQdiskPath(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        checkPath(path);
        byte[] buffer = new byte[256 * 1024];
        int length = 0;

        File file = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            while ((length = inputStream.read(buffer)) != -1) {
                for (int i = 0; i < length; ++i) {
                   fos.write(buffer);
                }
            }
        }
        catch (FileNotFoundException e) {
            logger.warn(e.getMessage());
            return null;
        }
        catch (IOException e) {
            logger.warn(e.getMessage());
            return null;
        }
        finally {
            try {
                if (null != fos) {
                    fos.flush();
                    fos.close();
                }
            }
            catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return getOuterPath(path);
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQdiskPath(), suffix));
    }

    @Override
    public byte[] downloadFile(String path) {
        String filePath = this.config.getQdiskPath() + File.separator + path;
        File file = new File(filePath);
        return getFileBuffer(file);
    }

    private void checkPath(String path) {
        if (StringUtils.isEmpty(path)) {
            throw new RRException("路径为空，不合法");
        }
        if (!path.startsWith(File.separator)) {
            throw new RRException("本地盘配置不是绝对路径");
        }
        String dirPath = path.substring(0, path.lastIndexOf(File.separator));
        File dir = new File(dirPath);
        if (!dir.exists()) {
            boolean ret = dir.mkdirs();
            if (!ret) {
                logger.warn("checkPath, make dir failed:[{}]", dir.getAbsolutePath());
                throw new RRException("目录创建失败：" + dirPath);
            }
        }
    }

    private String getOuterPath(String path) {
        Integer indexNumber = path.lastIndexOf("/",path.lastIndexOf("/")-1) + 1;
        String relativePath = path.substring(indexNumber);
        return ((ApiProperties) SpringContextUtils.getBean("apiProperties")).getOuterBaseUrl() + relativePath;
    }

    protected byte[] getFileBuffer(InputStream inStream, long fileLength) throws IOException {

        byte[] buffer = new byte[256 * 1024];
        byte[] fileBuffer = new byte[(int) fileLength];
        int count = 0;
        int length = 0;

        while ((length = inStream.read(buffer)) != -1) {
            for (int i = 0; i < length; ++i) {
                fileBuffer[count + i] = buffer[i];
            }
            count += length;
        }
        return fileBuffer;
    }

    protected byte[] getFileBuffer(File file) {
        if (file == null) {
            return null;
        }
        // byte[] fileBuff = FileUtils.readFileToByteArray(file);
        long fileLength = file.length();
        InputStream inputStream = null;
        byte[] fileBuff = null;
        try {
            inputStream = new FileInputStream(file);
            fileBuff = getFileBuffer(inputStream, fileLength);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            fileBuff = null;
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return fileBuff;
    }

    protected byte[] getFileBuffer(MultipartFile file) {
        if (file == null) {
            return null;
        }
        long fileLength = file.getSize();
        InputStream inputStream = null;
        byte[] fileBuff = null;

        try {
            inputStream = file.getInputStream();
            fileBuff = getFileBuffer(inputStream, fileLength);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            fileBuff = null;
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return fileBuff;
    }
}
