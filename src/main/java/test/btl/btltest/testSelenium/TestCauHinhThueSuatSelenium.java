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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCauHinhThueSuatSelenium {
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
        WebElement btnCauHinhThue = this.driver.findElement(By.xpath("//*[@id=\"nav-accordion\"]/li/a[3]"));
        btnCauHinhThue.click();
        Thread.sleep(500);

    }

    @After
    public void finish() throws InterruptedException {
        Thread.sleep(1000);
        this.driver.quit();
    }
    //kiểm tra cấu hình thuế suất khi thực hiện đầy đủ các bước và mức thuế suất nhập đúng định dạng
    @Test
    public void KiemTraCauHinhThueSuatDayDuCacBuocVaMucThueSuatDungDinhDang() throws InterruptedException {
        //thực hiện các bước
        //chọn loại thuế TNCN
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/button"))).perform();
        Thread.sleep(1000);
        WebElement txtThueTNCN = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div/a[1]"));
        txtThueTNCN.click();
        Thread.sleep(1000);
        //chọn thuế suất
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/div/button"))).perform();
        WebElement txtThueSuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/div/div/a[4]"));
        txtThueSuat.click();
        Thread.sleep(1000);
        //lấy mức thuế suất ban đầu
        WebElement txtMucThueSuatBanDau = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[2]/p[2]"));
        final String dulieubandau = txtMucThueSuatBanDau.getText();
        //nhập mức thuế suất test
        String mucthuesuattest = "1.1";
        WebElement txtMucThueSuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/input[1]"));
        txtMucThueSuat.sendKeys(mucthuesuattest);
        Thread.sleep(1000);
        WebElement btnLuu = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/button[2]"));
        btnLuu.click();
        //quay lại trang cấu hình thuế từ trang thông báo
        Thread.sleep(1000);
        WebElement btnQuaylai = this.driver.findElement(By.xpath("/html/body/a"));
        btnQuaylai.click();
        //kiểm tra dữ liệu trong db đã thay đổi so với dữ liệu vừa nhập
        Thread.sleep(1000);
        //chọn loại thuế TNCN trước
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/button"))).perform();
        WebElement txtThueTNCN1 = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div/a[1]"));
        txtThueTNCN1.click();
        Thread.sleep(1000);
        //chọn thuế suất trước
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/div/button"))).perform();
        WebElement txtThueSuat1 = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/div/div/a[4]"));
        txtThueSuat1.click();
        Thread.sleep(1000);
        //lấy mức thuế suất trong database
        WebElement txtMucThueSuatLucSau = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[2]/p[2]"));
        String dulieusau = txtMucThueSuatLucSau.getText();
        //nhập lại dữ liệu ban đầu để thực hiện rollback
        WebElement txtMucThueSuat1 = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/input[1]"));
        txtMucThueSuat1.sendKeys(dulieubandau);
        Thread.sleep(1000);
        WebElement btnLuu1 = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/button[2]"));
        btnLuu1.click();
        //so sánh mức thuế suất test với mức thuế suất trong database
        Assert.assertEquals(dulieusau,mucthuesuattest);

    }
    //kiểm tra cấu hình thuế suất khi thực hiện đầy đủ các bước và mức thuế suất nhập đúng định dạng
    @Test
    public void KiemTraCauHinhThueSuatDayDuCacBuocVaMucThueSuatSaiDinhDang() throws InterruptedException {
        //thực hiện các bước
        //chọn loại thuế TNCN
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/button"))).perform();
        Thread.sleep(1000);
        WebElement txtThueTNCN = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div/a[1]"));
        txtThueTNCN.click();
        Thread.sleep(1000);
        //chọn thuế suất
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/div/button"))).perform();
        WebElement txtThueSuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/div/div/a[4]"));
        txtThueSuat.click();
        Thread.sleep(1000);
        //lấy mức thuế suất ban đầu
        WebElement txtMucThueSuatBanDau = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[2]/p[2]"));
        final String dulieubandau = txtMucThueSuatBanDau.getText();
        //nhập mức thuế suất test
        String mucthuesuattest = "123abc";
        WebElement txtMucThueSuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/input[1]"));
        txtMucThueSuat.sendKeys(mucthuesuattest);
        Thread.sleep(1000);
        WebElement btnLuu = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/button[2]"));
        btnLuu.click();

        Thread.sleep(1000);
        WebElement txtThongbao = this.driver.findElement(By.xpath("/html/body/h3"));

        Assert.assertEquals("dữ liệu nhập không phải đúng định dạng float (ví dụ đúng : 0.5)",txtThongbao.getText());

    }

    //kiểm tra cấu hình thuế suất khi không chọn loại thuế TNCN
    @Test
    public void KiemTraCauHinhThueSuatKhongChonLoaiThueTNCN() throws InterruptedException {
        String mucthuesuattest = "123abc";
        WebElement txtMucThueSuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/input[1]"));
        txtMucThueSuat.sendKeys(mucthuesuattest);
        Thread.sleep(1000);
        WebElement btnLuu = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/button[2]"));
        btnLuu.click();

        Thread.sleep(1000);
        WebElement txtThongbao = this.driver.findElement(By.xpath("/html/body/h3"));

        Assert.assertEquals("chưa chọn thuế TNCN",txtThongbao.getText());

    }

    //kiểm tra cấu hình thuế suất khi không chọn loại thuế TNCN nhưng bấm chọn thuế suất
    @Test
    public void KiemTraCauHinhThueSuatKhongChonLoaiThueTNCNNhungBamChonThueSuat() throws InterruptedException {
        WebElement btnThuesuat = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/form/div[3]/div/button"));
        btnThuesuat.click();
        Thread.sleep(1000);
        WebElement txtThongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("chưa chọn thuế TNCN",txtThongbao.getText());

    }


}
