$(document).ready(function(){
    var contacts,contactString = AddCustomerInterface.getContacts();
    if(null!=contactString && ""!=contactString){
        contacts = contactString.split(";");
    }
    var currentFocus;
    $(".customer_add_form").on("submit",function(e){
    e.preventDefault();
        var reg = /^(([+]{1}\d{2}|0){0,1}[ \.\-]{0,1}(\d{3}[ \.\-]{0,1}(\d{3})[ \.\-]{0,1}(\d{4})))$/;
        var focus,error=false;
        if(""==$("#name").val()){
            if(null==focus)focus=$("#name");
            $("#name").setCustomValidity('Please Enter Name');
            error = true;
        }
        if(""==$("#phone").val()){
            if(null==focus)focus=$("#phone");
            $("#phone").setCustomValidity('Please Enter valid Phone Number');
            error = true;
        }
        if (!reg.test($("#phone").val())){
            if(null==focus)focus=$("#phone");
            $("#phone").setCustomValidity('Please Enter valid Phone Number');
            error = true;
        }
        if(0==$("#address").val().length){
            if(null==focus)focus=$("#address");
            $("#address").setCustomValidity('Please Enter Address');
            error = true;
        }
        if(error)focus.focus();
        else{
            var resp = AddCustomerInterface.showToast("ajith");
        }
    });
    $("#name").on("invalid", function(){
        this.setCustomValidity('Please Enter valid Name');
    });
    $("#name").on("input", function(){
        this.setCustomValidity('');
        var a, b, i, name = $("#name"), phone = $("#phone"), val = $(this).val();
        $("#" + name.attr("id") + "autocomplete-list").remove();
        $("#" + phone.attr("id") + "autocomplete-list").remove();
        if (!val) { return false; }
        currentFocus = - 1;
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        $(this).after(a);
        for (var i = 0; i < contacts.length; i++){
        var contact = contacts[i].split(":");
            if (contact[0].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                b = document.createElement("DIV");
                b.innerHTML = "<strong>" + contact[0].substr(0, val.length) + "</strong>";
                b.innerHTML += contact[0].substr(val.length);
                b.innerHTML += "<input type='hidden' value='" + contact[0] + "'>";
                b.innerHTML += "<input type='hidden' value='" + contact[1] + "'>";
                b.addEventListener("click", function(e) {
                    name.val(this.getElementsByTagName("input")[0].value);
                    var temp = this.getElementsByTagName("input")[1].value.split(",");
                    $("#" + name.attr("id") + "autocomplete-list").remove();
                    if(temp.length == 0)phone.val(temp[0]);
                    else{
                        val = phone.val();
                        currentFocus = - 1;
                        a = document.createElement("DIV");
                        a.setAttribute("id", phone.attr("id") + "autocomplete-list");
                        a.setAttribute("class", "autocomplete-items");
                        phone.after(a);
                        for (var i = 0; i < temp.length; i++){
                            if (temp[i].substr(0, val.length) == val) {
                                b = document.createElement("DIV");
                                b.innerHTML = "<strong>" + temp[i].substr(0, val.length) + "</strong>";
                                b.innerHTML += temp[i].substr(val.length);
                                b.innerHTML += "<input type='hidden' value='" + temp[i] + "'>";
                                b.addEventListener("click", function(e) {
                                    phone.val(this.getElementsByTagName("input")[0].value);
                                    $("#" + phone.attr("id") + "autocomplete-list").remove();
                                    });
                                a.append(b);
                            }
                        }
                        phone.on("keydown", function(e) {
                            var x = $("#" + phone.attr("id") + "autocomplete-list");
                            if (x) x = x.find("div");
                            if (e.keyCode == 40) {
                                currentFocus++;
                                addActive(x);
                            }
                            else if (e.keyCode == 38) {
                                currentFocus--;
                                addActive(x);
                            }
                            else if (e.keyCode == 13) {
                                e.preventDefault();
                                if (currentFocus > - 1) {
                                    if (x) x[currentFocus].click();
                                }
                            }
                        });
                    }
                });
                a.append(b);
            }
        }
        name.on("keydown", function(e) {
            var x = $("#" + this.id + "autocomplete-list");
            if (x) x = x.find("div");
            if (e.keyCode == 40) {
                currentFocus++;
                addActive(x);
            }
            else if (e.keyCode == 38) {
                currentFocus--;
                addActive(x);
            }
            else if (e.keyCode == 13) {
                e.preventDefault();
                if (currentFocus > - 1) {
                    if (x) x[currentFocus].click();
                }
            }
        });
        $("body").on("click",function(){
            $("#" + name.attr("id") + "autocomplete-list").remove();
        });
    });
    $("#name").focusout(function(){
        if ("" == $(this).val())
            this.setCustomValidity('Please Enter valid Name');
    });
    $("#phone").on("invalid", function(){
        this.setCustomValidity('Please Enter valid Phone Number');
    });
    $("#phone").on("input", function(){
        var reg = /^(([+]{1}\d{2}|0){0,1}[ \.\-]{0,1}(\d{3}[ \.\-]{0,1}(\d{3})[ \.\-]{0,1}(\d{4})))$/;
        if (!reg.test($(this).val()))
            this.setCustomValidity('Please Enter valid Phone Number');
        else
            this.setCustomValidity('');
    });
    $("#phone").focusout(function(){
        if ("" == $(this).val())
            this.setCustomValidity('Please Enter valid Phone Number');
    });
    $("#address").on("invalid", function(){
        this.setCustomValidity('Please Enter Address');
    });
    $("#address").on("input", function(){
        this.setCustomValidity('');
    });
    $("#address").focusout(function(){
        if ("" == $(this).val())
            this.setCustomValidity('Please Enter Address');
    });
    function addActive(x) {
        if (!x) return false;
        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);
        x[currentFocus].classList.add("autocomplete-active");
    }
    function removeActive(x) {
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }
    function closeAllLists(elmnt) {
        var x = $(".autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].remove();
            }
        }
    }
});