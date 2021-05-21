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

public class TestXuatBaoCaoThueSelenium {
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
        WebElement btnCauHinhThue = this.driver.findElement(By.xpath("//*[@id=\"nav-accordion\"]/li/a[2]"));
        btnCauHinhThue.click();
        Thread.sleep(500);

    }
    @After
    public void finish() throws InterruptedException {
        Thread.sleep(1000);
        this.driver.quit();
    }

    //Kiểm tra xuất báo cáo thống kê thực hiện đủ các bước, theo năm nhập đúng định dạng
    @Test
    public void KiemTraXuatBaoCaoThucHienDuBuocVaNamNhapDungDinhDang() throws InterruptedException {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/button"))).perform();

        Thread.sleep(1000);

        WebElement txtLoaithueTNCN = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/div/a[1]"));
        txtLoaithueTNCN.click();

        Thread.sleep(1000);

        WebElement txtNam = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[2]/div/div[1]/input"));
        txtNam.sendKeys("2020");

        Thread.sleep(1000);

        WebElement btnXuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[2]/div/div[2]/input"));
        btnXuat.click();

        Thread.sleep(1000);

        WebElement loaithue = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/table/tbody/tr/td[2]"));

        Assert.assertEquals("từ hoạt động kinh doanh",loaithue.getText());
    }

    //Kiểm tra xuất báo cáo thống kê thực hiện đủ các bước, theo năm nhập đúng định dạng
    @Test
    public void KiemTraXuatBaoCaoThucHienDuBuocVaNamNhapSaiDinhDang() throws InterruptedException {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/button"))).perform();

        Thread.sleep(1000);

        WebElement txtLoaithueTNCN = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/div/a[1]"));
        txtLoaithueTNCN.click();

        Thread.sleep(1000);

        WebElement txtNam = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[2]/div/div[1]/input"));
        txtNam.sendKeys("2020abc");

        Thread.sleep(1000);

        WebElement btnXuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[2]/div/div[2]/input"));
        btnXuat.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));

        Assert.assertEquals("dữ liệu không hợp lệ, có chứa kí tự khác ngoài số",thongbao.getText());
    }

    //Kiểm tra xuất báo cáo thống kê thực hiện đủ các bước, theo tháng nhập đúng định dạng
    @Test
    public void KiemTraXuatBaoCaoThucHienDuBuocVaThangNhapDungDinhDang() throws InterruptedException {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/button"))).perform();

        Thread.sleep(1000);

        WebElement txtLoaithueTNCN = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/div/a[1]"));
        txtLoaithueTNCN.click();

        Thread.sleep(1000);

        WebElement txtNam = this.driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        js.executeScript("document.getElementById('myInput').value='2020-09';",txtNam);

        Thread.sleep(1000);

        WebElement btnXuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[1]/div/div[2]/input"));
        btnXuat.click();

        Thread.sleep(1000);

        WebElement loaithue = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/table/tbody/tr/td[2]"));

        Assert.assertEquals("từ hoạt động kinh doanh",loaithue.getText());
    }

    //Kiểm tra xuất báo cáo thống kê thực hiện đủ các bước, theo tháng nhập đúng định dạng
    @Test
    public void KiemTraXuatBaoCaoThucHienDuBuocVaThangKhongNhap() throws InterruptedException {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/button"))).perform();

        Thread.sleep(1000);

        WebElement txtLoaithueTNCN = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/div/a[1]"));
        txtLoaithueTNCN.click();

        Thread.sleep(1000);

//        WebElement txtNam = this.driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
//        JavascriptExecutor js = (JavascriptExecutor) this.driver;
//
//        js.executeScript("document.getElementById('myInput').value='2020-09';",txtNam);

//        Thread.sleep(1000);

        WebElement btnXuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[1]/div/div[2]/input"));
        btnXuat.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));

        Assert.assertEquals("chưa chọn tháng",thongbao.getText());
    }

    //Kiểm tra xuất báo cáo thống kê không chọn loại thuế TNCN
    @Test
    public void KiemTraXuatBaoCaoKhongChonLoaiThueTNCNVaThangNhapDungDinhDang() throws InterruptedException {
        WebElement txtNam = this.driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        js.executeScript("document.getElementById('myInput').value='2020-09';",txtNam);

        Thread.sleep(1000);

        WebElement btnXuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[1]/div/div[2]/input"));
        btnXuat.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));

        Assert.assertEquals("chưa chọn loại thuế TNCN",thongbao.getText());
    }
    //Kiểm tra xuất báo cáo thống kê không chọn loại thuế TNCN
    @Test
    public void KiemTraXuatBaoCaoKhongChonLoaiThueTNCNVaNamNhapDungDinhDang() throws InterruptedException {
        WebElement txtNam = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[2]/div/div[1]/input"));
        txtNam.sendKeys("2020");

        Thread.sleep(1000);

        WebElement btnXuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[2]/form[2]/div/div[2]/input"));
        btnXuat.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));

        Assert.assertEquals("chưa chọn loại thuế TNCN",thongbao.getText());
    }
}
