var ctx = $('#ctx').val();
(function($) {
    $("#idCardFront").uploadPreview({
        Img: "ocImgPr",
        Callback: function () {
            checkImg("idCardFront","personIdFrontPicPath",1,1,"ocImgPr");
        }
    });
    $("#idCardContrary").uploadPreview({
        Img: "ocImgPr2",
        Callback: function () {
            checkImg("idCardContrary","personIdFrontPicPath",1,1,"ocImgPr2");
        }
    });
    $("#uploadPermit").uploadPreview({
        Img: "ocImgPr3",
        Callback: function () {
            checkImg("uploadPermit","personIdFrontPicPath",1,1,"ocImgPr3");
        }
    });
    $("#uploadCertificate").uploadPreview({
        Img: "ocImgPr4",
        Callback: function () {
            checkImg("uploadCertificate","personIdFrontPicPath",1,1,"ocImgPr4");
        }
    });
    $("#account").uploadPreview({
        Img: "ocImgPr5",
        Callback: function () {
            checkImg("account","personIdFrontPicPath",1,1,"ocImgPr5");
        }
    });
    $("#identification").uploadPreview({
        Img: "ocImgPr6",
        Callback: function () {
            checkImg("identification","personIdFrontPicPath",1,1,"ocImgPr6");
        }
    });
    $("#personImg").uploadPreview({
        Img: "ocImgPr7",
        Callback: function () {
            checkImg("personImg","personIdFrontPicPath",1,1,"ocImgPr7");
        }
    });
    $("#bankImg").uploadPreview({
        Img: "ocImgPr8",
        Callback: function () {
            checkImg("bankImg","personIdFrontPicPath",1,1,"ocImgPr8");
        }
    });
    $("#businessImg").uploadPreview({
        Img: "ocImgPr9",
        Callback: function () {
            checkImg("businessImg","personIdFrontPicPath",1,1,"ocImgPr9");
        }
    });
    $("#filing").uploadPreview({
        Img: "ocImgPr10",
        Callback: function () {
            checkImg("filing","personIdFrontPicPath",1,1,"ocImgPr10");
        }
    });
    function checkImg(fileUp,filePath,picType,newType,Img){
        var fileName = document.getElementById(fileUp).value;
        var blob = document.getElementById(fileUp).files[0];
        var BYTES_PER_CHUNK = 2048000; // 1MB chunk sizes.
        var SIZE = blob.size;
        var start = 0;
        var end = BYTES_PER_CHUNK;
        if(SIZE > BYTES_PER_CHUNK){
            $.toptip("文件大小超过限制2M");
            $("#"+Img).attr('src','');
            return;
        }
        while(start < SIZE) {
            upload(blob.slice(start, end),fileName,fileUp,filePath,picType,newType);
            start = end;
            end = start + BYTES_PER_CHUNK;
        }
    };
    function upload(blobOrFile,fileName,fileUp,filePath,picType,newType) {
        var fd = new FormData();
        var merId = 1;
        var url = ctx+"/wxbase/upload/uploadImage.html?picName="+fileUp+"&picType="+picType+"&merId="+merId+"&newType="+newType;
        fd.append('file',blobOrFile);
        fd.append('fileName',fileName);
        /*loading();*/
        $.ajax({
            url:url,
            type:'POST',
            data:fd,
            processData: false,  // 告诉jQuery不要去处理发送的数据
            contentType: false,
            asysn:false,
            success:function(ds){
                if(ds.success){
//					var data = jQuery.parseJSON(ds.content);
                    $.hideLoading();
                    $("#" + filePath).val(ds.content);
                    /*var ImgWeight = ($(".addCard img").height())/2;
                    $(".addCard img").css("margin-top",-ImgWeight+"px");*/
/*                    var fileUrl=filePath.replace("Path","Url");
                    $("#" + fileUrl).val(ds.content.wftUrl);*/
                    $.toptip("上传成功", 3000, 'success');
                }else{
                    $.toptip("上传失败", 3000, 'error');
                }

            }

        });
    }

})(jQuery);
