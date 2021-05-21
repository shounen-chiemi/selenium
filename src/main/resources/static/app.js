function taxCal() {
    let income = +document.getElementById('income').value;
    if (income === 0) {
        alert("Mời nhập lại");
    } else {
        let reduceFix = +document.getElementById('reduceFix').value;
        let personDepend = +document.getElementById('personDepend').value;
        let societyIns = +document.getElementById('societyIns').value;
        let healthIns = +document.getElementById('healthIns').value;
        let joblessIns = +document.getElementById('joblessIns').value;
        let retireFund = +document.getElementById('retireFund').value;
        if (retireFund > 1000000) {
            alert("Lỗi, giá trị của giảm trì quỹ hưu trí phải nhỏ hơn 1000000");
        } else {
            let reduce = reduceFix + personDepend * 3600000 + (societyIns + healthIns + joblessIns) / 100 * income + retireFund;
            let afterReduce = income - reduce;
            document.getElementById('taxMoney').innerHTML = "<b>" + afterReduce + "VND" + "</b>";
            if (afterReduce / 1000000 <= 5) {
                let tax = afterReduce * 0.05;
                document.getElementById('tax').innerHTML = "<b>" + Math.floor(tax) + " VND" + "</b>";
            } else if (afterReduce / 1000000 <= 10) {
                let tax = 0.25 * 1000000 + afterReduce * 0.1;
                document.getElementById('tax').innerHTML = "<b>" + Math.floor(tax) + " VND" + "</b>";
            } else if (afterReduce / 1000000 <= 18) {
                let tax = 0.75 * 1000000 + afterReduce * 0.15;
                document.getElementById('tax').innerHTML = "<b>" + Math.floor(tax) + " VND" + "</b>";
            } else if (afterReduce / 1000000 <= 32) {
                let tax = 1.95 * 1000000 + afterReduce * 0.20;
                document.getElementById('tax').innerHTML = "<b>" + Math.floor(tax) + " VND" + "</b>";
            } else if (afterReduce / 1000000 <= 52) {
                let tax = 4.75 * 1000000 + afterReduce * 0.25;
                document.getElementById('tax').innerHTML = "<b>" + Math.floor(tax) + " VND" + "</b>";
            } else if (afterReduce / 1000000 <= 80) {
                let tax = 9.75 * 1000000 + afterReduce * 0.3;
                document.getElementById('tax').innerHTML = "<b>" + Math.floor(tax) + " VND" + "</b>";
            } else {
                let tax = 18.15 * 1000000 + afterReduce * 0.35;
                document.getElementById('tax').innerHTML = "<b>" + Math.floor(tax) + " VND" + "</b>";
            }
        }
    }
}