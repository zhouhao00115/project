package com.zhaopin.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 多线程共享流 实现文件写入
     *
     * @param str
     * @param writer
     */
    public static synchronized void storeString(String str, BufferedWriter writer) {
        try {
            if (!(null == str && "".equals(str))) {
                if (null != writer) {
                    writer.append(str);
                    writer.newLine();//换行
                    writer.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
                }
            }
        } catch (IOException e) {
            log.error("写入失败");
        }
    }

    public static String getFirstLine(File file) {
        BufferedReader reader = null;
        FileInputStream stream = null;
        InputStreamReader isr = null;
        String temp = null;
        try {
            stream = new FileInputStream(file);
            isr = new InputStreamReader(stream, "UTF-8");
            reader = new BufferedReader(isr);
            temp = reader.readLine();
            isr.close();
            stream.close();
            reader.close();
        } catch (IOException e) {
            log.error("文件读取异常" + file.getAbsolutePath(), e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
        }
        return temp;
    }

    /**
     * 获取文件行数
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static int getLine(File file) throws IOException {
        int num = 0;
        BufferedReader reader = null;
        FileInputStream stream = null;
        InputStreamReader isr = null;
        try {
            stream = new FileInputStream(file);
            isr = new InputStreamReader(stream, "UTF-8");
            reader = new BufferedReader(isr);
            while ((reader.readLine()) != null) {
                // 显示行号
                num++;
            }
            isr.close();
            stream.close();
            reader.close();
        } catch (IOException e) {
            log.error("文件读取异常" + file.getAbsolutePath(), e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
        }
        return num;
    }


    /**
     * 获取文件的行列表
     *
     * @param fileName 文件名(含全路径)
     * @return list对象
     */
    public static List<String> getLastList(String fileName) {
        File file = new File(fileName);
        return getLastList(file);
    }

    /**
     * 读取存放在文件中的list对象
     *
     * @param file
     * @return
     */
    public static List<String> getLastList(File file) {
        List<String> list = new ArrayList<>();
        BufferedReader reader = null;
        FileInputStream stream = null;
        InputStreamReader isr = null;
        try {
            stream = new FileInputStream(file);
            isr = new InputStreamReader(stream, "GBK");
            reader = new BufferedReader(isr);
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            //读取第一个文件
            while (!StringUtil.isNull(tempString = reader.readLine())) {
                // 显示行号
                list.add(tempString.trim());
            }
            isr.close();
            stream.close();
            reader.close();
        } catch (IOException e) {
            log.error("文件读取异常" + file.getAbsolutePath(), e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    log.error("文件关闭异常" + file.getAbsolutePath(), e1);
                }
            }
        }
        return list;
    }

    public static byte[] toByteArray(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }
}
