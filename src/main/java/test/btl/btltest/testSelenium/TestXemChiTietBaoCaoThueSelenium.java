package test.btl.btltest.testSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Iterator;
import java.util.Set;

public class TestXemChiTietBaoCaoThueSelenium {
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
    //Kiểm tra chuyển hướng sang trang xem báo cáo xem chi tiết
    @Test
    public void KiemTraChuyenHuongSangTrangXemChiTiet() throws InterruptedException {
        WebElement btnXemchitiet = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[9]/form/a"));
        btnXemchitiet.click();

        Thread.sleep(1000);

        Set<String> s=driver.getWindowHandles();
        for(String w : s)
        {
            System.out.println(w);
        }
        Iterator<String> I1= s.iterator();
        this.driver.switchTo().window(I1.next());
        this.driver.switchTo().window(I1.next());
        String urlmoi = this.driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8089/list-taxpayer/idBaocaothue=1/?",urlmoi);
    }
    //Kiểm tra dữ liệu sau khi chuyển hướng sang trang xem báo cáo xem chi tiết
    @Test
    public void KiemTraDuLieuSauKhiChuyenHuongSangTrangXemChiTiet() throws InterruptedException {
        WebElement dulieutruockhichuyen = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[1]"));
        String dulieutruoc = dulieutruockhichuyen.getText();

        Thread.sleep(100);

        WebElement btnXemchitiet = this.driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[1]/td[9]/form/a"));
        btnXemchitiet.click();

        Thread.sleep(1000);

        Set<String> s=driver.getWindowHandles();

        Iterator<String> I1= s.iterator();
        this.driver.switchTo().window(I1.next());
        this.driver.switchTo().window(I1.next());
        Thread.sleep(500);
        WebElement dulieusaukhichuyen = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[3]/div/div[1]/div/div/input"));
        String dulieusau = dulieusaukhichuyen.getAttribute("value");
        Assert.assertEquals(dulieutruoc,dulieusau);
    }


}
