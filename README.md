# pinyin4j-chinese-simplified
 [![Maven Central](https://img.shields.io/maven-central/v/cn.lalaki/pinyin4j-chinese-simplified.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/cn.lalaki/pinyin4j-chinese-simplified/)
[![License](https://img.shields.io/badge/License-GNU%20GPLv2%20-darkred)](COPYING.txt)

**2024-06-04 已经重新生成拼音数据文件，全部拼音数据来自: https://www.zdic.net/**

### Gradle

```gradle
implementation("cn.lalaki:pinyin4j-chinese-simplified:1.0.6")
```

### API
```java
// 将中文字符转换为拼音，返回String数组，不会为null，如果数组长度为0就是没有相应的拼音数据
BasePinyinHelper.toHanyuPinyinStringArray(context,'好')

// 上面的方法将返回new String[]{"hao3","hao4"}，数字是拼音声调
```
```java
// 将中文句子转换为拼音数组列表，异步回调
BasePinyinHelper.toHanyuPinyinStringArray(context,
        "你好吗", new BasePinyinHelper() {
            @Override
            public void onPinyinStringArrayList(List<String[]> pinyinList) {
                for(String[] pinyin:pinyinList){
                    Log.d(TAG, String.join(",", pinyin));
                }
            }
        });
```
### About

Readme of pinyin4j

Table of content
I. 	Main Features
II. Future work
III.How to install
IV. Getting start
V. 	Author
VI.	Copyright

I. Main Features

1. Support conversion from Chinese character (both Simplified and Tranditional) to different Chinese Romanization systems
2. Support various target Chinese Romanization systems, including Hanyu Pinyin, Tongyong Pinyin, Wade-Giles, MPS2 (Mandarin Phonetic Symbols 2), Yale and Gwoyeu Romatzyh
2. Support multiple pronunciations of a single Chinese character
3. Several output format
3.1. Uppercase or lowercase
3.2. v or u: or unicode ü
3.3. unicode output with tone numbers, with tone marks or without tone

II. Future work

1. Provide format functions to other Chinese Romanization systems excluding Hanyu Pinyin
2. Provide Pinyin to Chinese
3. Provide coverage test to discover unknown Chinese characters

III. How to install

1. Add lib/pinyin4j-2.5.0.jar to your classpath
2. Import necessary class files

import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.* // this import is necessary only when you use the output format function

IV. Getting start

1. open command-line window, type
cd ${pinyin-install-dir}\lib

2. In lib directory, there is a JAR file named pinyin4j-2.5.0.jar, type
java -jar pinyin4j-2.5.0.jar
run the GUI demo application

3. If you want to modify the source and compile it, you may need download sparta.jar from http://sparta-xml.sourceforge.net/ and add it to classpath.
(Pinyin4j adds sparta-xml library into pinyin4j-2.5.0.jar file already. Therefore you don't need the sparta.jar if you simply use pinyin4j without re-compiling it.)

NOTE: the java files using pinyin4j should be save as unicode-supporting encoding, such as UTF-8

V. Author

Li Min (xmlerlimin@gmail.com)

You can reach me at:
Blogger: http://lemann.blogspot.com/
My.Opera: http://my.opera.com/lemann/

VI.	Copyright
1. The dictionary file contains Chinese Unicode to Hanyu Pinyin mapping is got from internet, named "uc-to-py.tbl". The author is stolfi.
2. The comparison table among Tongyong Pinyin, Wade-Giles, MPS2, Yale, Hanyu Pinyin, and Gwoyeu Romatzyh are got from http://www.pinyin.info/romanization/compare/tongyong.html
3. The algorithm for placing tone marks is got from http://en.wikipedia.org/wiki/Pinyin#Rules_for_placing_the_tone_mark
4. The XML and XPath parsing library is got from http://sparta-xml.sourceforge.net/
5. The release of Pinyin4j library is based on GNU GENERAL PUBLIC LICENSE (GPL). See more details about GPL, please refer to COPYING.txt
