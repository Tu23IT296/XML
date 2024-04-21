package XML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DirectoryListingToXML {
    public static void main(String[] args) {
        // Nhập đường dẫn từ người dùng
        String directoryPath = ""; // Đường dẫn thư mục sẽ được liệt kê
        if (args.length > 0) {
            // Nếu có đối số dòng lệnh, sử dụng đối số đầu tiên làm đường dẫn
            directoryPath = args[0];
        } else {
            // Nếu không, yêu cầu người dùng nhập từ bàn phím
            System.out.print("Nhập đường dẫn của thư mục cần liệt kê: ");
            Scanner scanner = new Scanner(System.in);
            directoryPath = scanner.nextLine();
            scanner.close();
        }

        // Tạo đối tượng File từ đường dẫn được cung cấp
        File directory = new File(directoryPath);

        // Kiểm tra sự tồn tại của thư mục
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Thư mục không tồn tại hoặc đường dẫn không đúng!");
            return;
        }

        // Tạo một đối tượng StringBuilder để xây dựng XML
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<directory>\n");

        // Gọi phương thức đệ quy để liệt kê thư mục dưới dạng XML
        listDirectory(directory, xmlBuilder, 1);

        xmlBuilder.append("</directory>");

        // Ghi XML ra file
        try {
            FileWriter writer = new FileWriter("directory_listing.xml");
            writer.write(xmlBuilder.toString());
            writer.close();
            System.out.println("Cây thư mục đã được liệt kê và ghi vào file 'directory_listing.xml'");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi file XML: " + e.getMessage());
        }
    }

    // Phương thức đệ quy để liệt kê thư mục dưới dạng XML
    private static void listDirectory(File directory, StringBuilder xmlBuilder, int depth) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Nếu là thư mục, thêm thẻ mở
                    appendIndent(xmlBuilder, depth);
                    xmlBuilder.append("<" + file.getName() + ">\n");

                    // Gọi đệ quy cho thư mục con
                    listDirectory(file, xmlBuilder, depth + 1);

                    // Thêm thẻ đóng cho thư mục
                    appendIndent(xmlBuilder, depth);
                    xmlBuilder.append("</" + file.getName() + ">\n");
                } else {
                    // Nếu là tệp, thêm thẻ với tên tệp
                    appendIndent(xmlBuilder, depth);
                    xmlBuilder.append("<file>" + file.getName() + "</file>\n");
                }
            }
        }
    }

    // Phương thức để thêm dấu tab theo độ sâu của thư mục
    private static void appendIndent(StringBuilder builder, int depth) {
        for (int i = 0; i < depth; i++) {
            builder.append("\t");
        }
    }
}
