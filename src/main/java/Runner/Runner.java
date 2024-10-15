package Runner;

import PageObject.StCourier;
import PageObject.TcpIndia;
import utils.ExcelReadAndWrite;
import utils.PageControls;

import java.util.HashMap;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        ExcelReadAndWrite excelReadAndWrite = new ExcelReadAndWrite("./TestData.xlsx", "Sheet1");
        List<HashMap<String, String>> data = excelReadAndWrite.readExcelRowWise();

        PageControls pageControls = new PageControls();
        pageControls.launchBrowser("chrome");

        for (int i = 0; i < data.size(); i++) {
            String status = "";
            try {
                StCourier courier = new StCourier();
                TcpIndia tcpIndia = new TcpIndia();

                if (data.get(i).get("Need to execute").equalsIgnoreCase("Yes")) {
                    if (data.get(i).get("Courier").equalsIgnoreCase("stcourier"))
                        status = courier.getStatus(data.get(i).get("AwbNumber"));
                    else
                        status = tcpIndia.getStatus(data.get(i).get("AwbNumber"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            excelReadAndWrite.setExcelRowData((i + 1), 3, status);
        }

        pageControls.closeBrowser();


    }
}
