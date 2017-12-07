package serialisation.dataTimeSerializer.XML;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class XmlTimeSerialisation implements Converter {

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        LocalTime zdt = (LocalTime) source;
        writer.setValue(zdt.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return LocalTime.parse(reader.getValue(), DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(LocalTime.class);
    }
}