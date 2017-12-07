    package serialisation;

    import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import serialisation.dataTimeSerializer.XML.XmlDataSerialisation;
import serialisation.dataTimeSerializer.XML.XmlTimeSerialisation;

import java.io.*;
import java.util.ArrayList;

    public class XmlSerializeImpl<T> implements Serialisation<T> {
        private Class<T> clases;

        public Class<T> getClases() {
            return clases;
        }

        public void setClases(Class<T> clases) {
            this.clases = clases;
        }

        public XmlSerializeImpl(Class<T> clases) {
            this.clases = clases;
        }

        @Override
        public void toFile(ArrayList<T> object, File file) {
            XStream xStream = new XStream();
            xStream = new XStream(new DomDriver());
            xStream.autodetectAnnotations(true);
            xStream.registerConverter(new XmlDataSerialisation());
            xStream.registerConverter(new XmlTimeSerialisation());
            String strObj = xStream.toXML(object);
            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                writer.write(strObj);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public ArrayList<T> fromFile(File file) {
            XStream xStream = new XStream();
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
                return (ArrayList<T>) xStream.fromXML(fileReader);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }