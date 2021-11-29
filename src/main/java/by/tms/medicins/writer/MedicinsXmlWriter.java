package by.tms.medicins.writer;

import by.tms.medicins.entity.Drug;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

public class MedicinsXmlWriter {

        private final String fileName;

        public MedicinsXmlWriter(String fileName) {
            this.fileName = fileName;
        }

        public void write(List<Drug> medicinsList ) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
                bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
                bufferedWriter.newLine();
                bufferedWriter.write("<medicins>");
                bufferedWriter.newLine();
                bufferedWriter.write("drug");
                bufferedWriter.newLine();
                bufferedWriter.write("name");
                bufferedWriter.newLine();
                bufferedWriter.write("/name");
                bufferedWriter.newLine();
                bufferedWriter.write("pharm");
                bufferedWriter.newLine();
                bufferedWriter.write("/pharm");
                bufferedWriter.newLine();
                bufferedWriter.write("group");
                bufferedWriter.newLine();
                bufferedWriter.write("/group");
                bufferedWriter.newLine();
                bufferedWriter.write("analogs");
                bufferedWriter.newLine();
                bufferedWriter.write("versions");
                bufferedWriter.newLine();
                bufferedWriter.write("/versions");
                bufferedWriter.newLine();
                bufferedWriter.write("/drug");
                bufferedWriter.newLine();

//                for (DeviceType type : DeviceType.values()) {
//                    bufferedWriter.write("<deviceType name = \"" + type + "\">");
//                    bufferedWriter.newLine();
//                    bufferedWriter.write("<weight> " + weightMap.getOrDefault(type, 0.) + "</weight> ");
//                    bufferedWriter.newLine();
//                    bufferedWriter.write("<price>" + priceMap.getOrDefault(type, 0L) + "</price>");
//                    bufferedWriter.newLine();
//                    bufferedWriter.write("</deviceType>");
//                    bufferedWriter.newLine();
//                }
                bufferedWriter.write("</medicins>");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

