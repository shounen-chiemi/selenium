package test.btl.btltest.testSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestXemDanhSachBaoCaoThueSelenium {
    WebDriver driver;

    @Before
    public void start() throws InterruptedException {
        System.setProperty("webdriver.edge.driver","C:\\Users\\Hiep Le\\Desktop\\lib-springboot\\webdriver\\msedgedriver.exe");
        this.driver = new EdgeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("http://localhost:8089/index/");
        WebElement linkDangnhap = this.driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[6]/a"));
        linkDangnhap.click();
        Thread.sleep(1000);
        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        txtTK.sendKeys("admin1");
        txtMK.sendKeys("admin1");
        btnDangnhap.click();
        Thread.sleep(1000);

    }
    @After
    public void finish() throws InterruptedException {
        Thread.sleep(1000);
        this.driver.quit();
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo thành phố
    @Test
    public void KiemTraTimKiemTheoThanhPhoDaChon() throws InterruptedException {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[2]/form[1]/div/select"))).perform();

        WebElement txtTP = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[2]/form[1]/div/select/option[25]"));
        txtTP.click();
        Thread.sleep(1000);

        WebElement btnTimkiemTP = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[2]/form[1]/div/div/input"));
        btnTimkiemTP.click();

        Thread.sleep(1000);

        WebElement data = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[3]"));
        Assert.assertEquals("Hà Nội",data.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo thành phố nhưng chưa chọn thành phố
    @Test
    public void KiemTraTimKiemTheoThanhPhoChuaChon() throws InterruptedException {

        WebElement btnTimkiemTP = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[2]/form[1]/div/div/input"));
        btnTimkiemTP.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("chưa chọn thành phố",thongbao.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo năm
    @Test
    public void KiemTraTimKiemTheoNam() throws InterruptedException {

        WebElement txtNam = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[2]/form[2]/div/div[1]/input"));
        txtNam.sendKeys("2020");

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[2]/form[2]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement data = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[7]"));
        String nam = data.getText();
        String nam2020 = nam.substring(0,4);

        Assert.assertEquals("2020",nam2020);
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo năm nhập sai định dạng
    @Test
    public void KiemTraTimKiemTheoNamNhapSaiDinhDang() throws InterruptedException {

        WebElement txtNam = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[2]/form[2]/div/div[1]/input"));
        txtNam.sendKeys("2020abc");

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[2]/form[2]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("dữ liệu không hợp lệ, có chứa kí tự khác ngoài số",thongbao.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo tháng
    @Test
    public void KiemTraTimKiemTheoThang() throws InterruptedException {

        WebElement txtThang = this.driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        js.executeScript("document.getElementById('myInput').value='2020-09';",txtThang);

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[3]/form[1]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement data = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[2]"));

        Assert.assertNotEquals("",data.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo tháng chưa nhập dữ liệu
    @Test
    public void KiemTraTimKiemTheoThangChuaNhap() throws InterruptedException {

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[3]/form[1]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("chưa chọn tháng",thongbao.getText());
    }
    //Kiểm tra tìm kiếm báo cáo thuế theo ngày
    @Test
    public void KiemTraTimKiemTheoNgay() throws InterruptedException {

        WebElement txtNgay = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[3]/form[2]/div/div[1]/input"));
//        JavascriptExecutor js = (JavascriptExecutor) this.driver;
//
//        js.executeScript("document.getElementById('ngay').value='07-04-2020';",txtNgay);
        txtNgay.sendKeys("04-07-2020");

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[3]/form[2]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement data = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[2]"));

        Assert.assertNotEquals("",data.getText());
    }
    //Kiểm tra tìm kiếm báo cáo thuế theo ngày chưa nhập dữ liệu
    @Test
    public void KiemTraTimKiemTheoNgayChuaNhap() throws InterruptedException {

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[3]/form[2]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("chưa chọn ngày",thongbao.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo tên đúng định dạng
    @Test
    public void KiemTraTimKiemTheoTen() throws InterruptedException {

        WebElement txtTen = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[1]/div/div[1]/input"));
//        JavascriptExecutor js = (JavascriptExecutor) this.driver;
//
//        js.executeScript("document.getElementById('ngay').value='07-04-2020';",txtNgay);
        txtTen.sendKeys("Nguyễn Nhật Minh");

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[1]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement data = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[2]"));

        Assert.assertNotEquals("",data.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo tên chứa nhiều khoảng trắng
    @Test
    public void KiemTraTimKiemTheoTenChuaKhoangTrang() throws InterruptedException {

        WebElement txtTen = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[1]/div/div[1]/input"));
//        JavascriptExecutor js = (JavascriptExecutor) this.driver;
//
//        js.executeScript("document.getElementById('ngay').value='07-04-2020';",txtNgay);
        txtTen.sendKeys("       Nguyễn Nhật   Minh    ");

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[1]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement data = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[2]"));

        Assert.assertNotEquals("",data.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo tên sai định dạng
    @Test
    public void KiemTraTimKiemTheoTenSaiDinhDang() throws InterruptedException {

        WebElement txtTen = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[1]/div/div[1]/input"));
//        JavascriptExecutor js = (JavascriptExecutor) this.driver;
//
//        js.executeScript("document.getElementById('ngay').value='07-04-2020';",txtNgay);
        txtTen.sendKeys("abc123");

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[1]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("dữ liệu không hợp lệ, có chứa kí tự khác ngoài chữ cái",thongbao.getText());
    }
    //Kiểm tra tìm kiếm báo cáo thuế theo tên chưa nhập dữ liệu
    @Test
    public void KiemTraTimKiemTheoTenChuaNhap() throws InterruptedException {

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[1]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("chưa nhập tên",thongbao.getText());
    }
    //Kiểm tra tìm kiếm báo cáo thuế theo cmnd đúng định dạng
    @Test
    public void KiemTraTimKiemTheoCMND() throws InterruptedException {

        WebElement txtCMND = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[2]/div/div[1]/input"));
//        JavascriptExecutor js = (JavascriptExecutor) this.driver;
//
//        js.executeScript("document.getElementById('ngay').value='07-04-2020';",txtNgay);
        txtCMND.sendKeys("0942216667");

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[2]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement data = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[2]"));

        Assert.assertNotEquals("",data.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo cmnd sai định dạng
    @Test
    public void KiemTraTimKiemTheoCMNDSaiDinhDang() throws InterruptedException {

        WebElement txtCMND = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[2]/div/div[1]/input"));

        txtCMND.sendKeys("0942216667abc");

        Thread.sleep(1000);

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[2]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("dữ liệu không hợp lệ, có chứa kí tự khác ngoài số",thongbao.getText());
    }

    //Kiểm tra tìm kiếm báo cáo thuế theo cmnd chưa nhập dữ liệu
    @Test
    public void KiemTraTimKiemTheoCMNDChuaNhap() throws InterruptedException {

        WebElement btnTimkiem = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[4]/form[2]/div/div[2]/input"));
        btnTimkiem.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("chưa nhập chứng minh nhân dân",thongbao.getText());
    }
    //Kiểm tra tìm kiếm báo cáo thuế theo cmnd chưa nhập dữ liệu
    @Test
    public void KiemTraTimKiemTheoLoaiThueTNCN() throws InterruptedException {

        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[1]/div/button"))).perform();
        Thread.sleep(1000);
        WebElement txtThueTNCN = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[1]/div[1]/div/div/a[1]"));
        txtThueTNCN.click();

        Thread.sleep(1000);

        WebElement data = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[2]"));

        Assert.assertNotEquals("",data.getText());
    }


}
