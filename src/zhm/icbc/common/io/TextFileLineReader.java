/**
 * TextFileLineReader.java
 * io
 * 2018��5��24������9:44:11
 *
 */
package zhm.icbc.common.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author zhuheming
 * TextFileLineReader
 * 2018年5月24日下午10:02:06
 */
public class TextFileLineReader {
	public void read(String filePath, TextFileLineProcessor lineProcessor) throws IOException {
		File file = new File(filePath);
		read(file, lineProcessor);
	}

	public void read(File file, TextFileLineProcessor lineProcessor) throws IOException {
		if ((null == file) || (!(file.exists()))) {
			throw new IllegalArgumentException("file " + file + " invalid, may be not exists");
		}
		if (null == lineProcessor) {
			throw new IllegalArgumentException("line processor is null");
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			int lineNum = 0;
			while (null != (line = reader.readLine())) {
				++lineNum;
				lineProcessor.process(line, lineNum);
			}
		} finally {
			IOUtil.closeQuietly(reader);
		}
	}
}
