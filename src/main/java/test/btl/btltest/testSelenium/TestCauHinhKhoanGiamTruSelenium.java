package test.btl.btltest.testSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestCauHinhKhoanGiamTruSelenium {
    WebDriver driver;

    @Before
    public void start() throws InterruptedException {
        //đường dẫn chỗ lưu driver Edge
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
    //kiểm tra cấu hình khoản giảm trừ khi thực hiện đầy đủ các bước và mức thuế suất nhập đúng định dạng
    @Test
    public void KiemTraCauHinhKhoanGiamTruDayDuCacBuocVaMucGiamTruDungDinhDang() throws InterruptedException {
        //thực hiện các bước
        //chọn khoản giảm trừ
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/div/button"))).perform();
        Thread.sleep(1000);
        WebElement txtKhoanGiamTru = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/div/div/a[1]"));
        txtKhoanGiamTru.click();
        Thread.sleep(1000);
        //lấy mức giảm trừ ban đầu
        WebElement txtMucGiamTruBanDau = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[2]/p[2]"));
        final String dulieubandau = txtMucGiamTruBanDau.getText();
        //nhập mức giảm trừ test
        String mucgiamtrutest = "1000000";
        WebElement txtMucGiamTru = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/input[1]"));
        txtMucGiamTru.sendKeys(mucgiamtrutest);
        Thread.sleep(1000);
        WebElement btnLuu = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/button[2]"));
        btnLuu.click();
        //quay lại trang cấu hình thuế từ trang thông báo
        Thread.sleep(1000);
        WebElement btnQuaylai = this.driver.findElement(By.xpath("/html/body/a"));
        btnQuaylai.click();
        //kiểm tra dữ liệu trong db đã thay đổi so với dữ liệu vừa nhập
        Thread.sleep(1000);
        //chọn loại thuế TNCN trước
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/div/button"))).perform();
        WebElement txtKhoanGiamTru1 = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/div/div/a[1]"));
        txtKhoanGiamTru1.click();
        Thread.sleep(1000);
        //lấy mức thuế suất trong database
        WebElement txtMucGiamTruLucSau = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[2]/p[2]"));
        String dulieusau = txtMucGiamTruLucSau.getText();
        //nhập lại dữ liệu ban đầu để thực hiện rollback
        WebElement txtMucGiamTru1 = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/input[1]"));
        txtMucGiamTru1.sendKeys(dulieubandau);
        Thread.sleep(1000);
        WebElement btnLuu1 = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/button[2]"));
        btnLuu1.click();
        //so sánh mức thuế suất test với mức thuế suất trong database
        Assert.assertEquals(dulieusau,mucgiamtrutest);

    }

    //kiểm tra cấu hình khoản giảm trừ khi thực hiện đầy đủ các bước và mức thuế suất nhập sai định dạng
    @Test
    public void KiemTraCauHinhKhoanGiamTruDayDuCacBuocVaMucGiamTruSaiDinhDang() throws InterruptedException {
        //thực hiện các bước
        //chọn khoản giảm trừ
        Actions actions = new Actions(this.driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/div/button"))).perform();
        Thread.sleep(1000);
        WebElement txtKhoanGiamTru = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/div/div/a[1]"));
        txtKhoanGiamTru.click();
        Thread.sleep(1000);
        //lấy mức giảm trừ ban đầu
        WebElement txtMucGiamTruBanDau = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[2]/p[2]"));
        final String dulieubandau = txtMucGiamTruBanDau.getText();
        //nhập mức giảm trừ test
        String mucgiamtrutest = "100abc";
        WebElement txtMucGiamTru = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/input[1]"));
        txtMucGiamTru.sendKeys(mucgiamtrutest);
        Thread.sleep(1000);
        WebElement btnLuu = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/button[2]"));
        btnLuu.click();

        Thread.sleep(1000);
        WebElement txtThongbao = this.driver.findElement(By.xpath("/html/body/h3"));

        Assert.assertEquals("dữ liệu nhập không phải đúng định dạng int (ví dụ đúng : 10000)",txtThongbao.getText());

    }

    //kiểm tra cấu hình khoản giảm trừ khi không chọn khoản giảm trừ và mức thuế suất nhập sai định dạng
    @Test
    public void KiemTraCauHinhKhoanGiamTruKhongChonKhoanGiamTru() throws InterruptedException {

        String mucgiamtrutest = "100abc";
        WebElement txtMucGiamTru = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/div[3]/input[1]"));
        txtMucGiamTru.sendKeys(mucgiamtrutest);
        Thread.sleep(1000);
        WebElement btnLuu = this.driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[7]/form/button[2]"));
        btnLuu.click();

        Thread.sleep(1000);
        WebElement txtThongbao = this.driver.findElement(By.xpath("/html/body/h3"));

        Assert.assertEquals("chưa chọn khoản giảm trừ",txtThongbao.getText());

    }


}
