package test.btl.btltest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class TestSelenium {
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
    }

    @After
    public void finish() throws InterruptedException {
        Thread.sleep(1000);
        this.driver.quit();
    }

    //kiểm tra đăng nhập với tài khoản đúng và mật khẩu đúng
    @Test
    public void KiemTraDangNhapVoiTaiKhoanDungVaMatKhauDung() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        txtTK.sendKeys("admin1");
        txtMK.sendKeys("admin1");
        btnDangnhap.click();

        Thread.sleep(1000);

        String duongdansau = this.driver.getCurrentUrl();
        String duongdanmongdoi = "http://localhost:8089/list-taxpayer/page/1";

        Assert.assertEquals(duongdanmongdoi,duongdansau);

    }

    //kiểm tra đăng nhập với tài khoản đúng và mật khẩu sai
    @Test
    public void KiemTraDangNhapVoiTaiKhoanDungVaMatKhauSai() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        txtTK.sendKeys("admin1");
        txtMK.sendKeys("matkhausai");
        btnDangnhap.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("Đăng nhập thất bại, tài khoản hoặc mật khẩu không chính xác",thongbao.getText());

    }

    //kiểm tra đăng nhập với tài khoản sai và mật khẩu đúng
    @Test
    public void KiemTraDangNhapVoiTaiKhoanSaiVaMatKhauDung() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        txtTK.sendKeys("taikhoansai");
        txtMK.sendKeys("admin1");
        btnDangnhap.click();

        Thread.sleep(1000);

        String duongdansau = this.driver.getCurrentUrl();
        String duongdanmongdoi = "http://localhost:8089/loginuser";

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("Đăng nhập thất bại, tài khoản hoặc mật khẩu không chính xác",thongbao.getText());

    }

    //kiểm tra đăng nhập với tài khoản sai và mật khẩu sai
    @Test
    public void KiemTraDangNhapVoiTaiKhoanSaiVaMatKhauSai() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        txtTK.sendKeys("taikhoansai");
        txtMK.sendKeys("matkhausai");
        btnDangnhap.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("Đăng nhập thất bại, tài khoản hoặc mật khẩu không chính xác",thongbao.getText());

    }

    //kiểm tra đăng nhập với tài khoản bỏ trống
    @Test
    public void KiemTraDangNhapVoiTaiKhoanBoTrong() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
//        txtTK.sendKeys("");
        txtMK.sendKeys("admin1");
        btnDangnhap.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("Tài khoản không được để trống",thongbao.getText());

    }

    //kiểm tra đăng nhập với mật khẩu bỏ trống
    @Test
    public void KiemTraDangNhapVoiMatKhauBoTrong() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        txtTK.sendKeys("admin1");
        //txtMK.sendKeys("admin1");
        btnDangnhap.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("Tài khoản không được để trống",thongbao.getText());

    }

    //kiểm tra đăng nhập với mật khẩu bỏ trống, tài khoản bỏ trống
    @Test
    public void KiemTraDangNhapVoiMatKhauBoTrongVaTaiKhoanBoTrong() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        //txtTK.sendKeys("admin1");
        //txtMK.sendKeys("admin1");
        btnDangnhap.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("Tài khoản không được để trống",thongbao.getText());

    }

    //kiểm tra đăng nhập với tài khoản nhập khoảng trắng
    @Test
    public void KiemTraDangNhapVoiTaiKhoanNhapKhoangTrang() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        txtTK.sendKeys("       ");
        txtMK.sendKeys("admin1");
        btnDangnhap.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("Tài khoản không được chứa khoảng trắng",thongbao.getText());

    }

    //kiểm tra đăng nhập với tài mật khẩu khoảng trắng
    @Test
    public void KiemTraDangNhapVoiMatKhauNhapKhoangTrang() throws InterruptedException {


        WebElement txtTK = this.driver.findElement(By.xpath("//*[@id=\"uname\"]"));
        WebElement txtMK = this.driver.findElement(By.name("txtP"));
        WebElement btnDangnhap = this.driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/form/button"));
        txtTK.sendKeys("admin1");
        txtMK.sendKeys("        ");
        btnDangnhap.click();

        Thread.sleep(1000);

        WebElement thongbao = this.driver.findElement(By.xpath("/html/body/h3"));
        Assert.assertEquals("Mật khẩu không được chứa khoảng trắng",thongbao.getText());

    }
}

