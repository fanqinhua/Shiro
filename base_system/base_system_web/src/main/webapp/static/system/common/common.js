// var globalPath = '/winecore-httpserver/api/v1';
var apiPath = '/orderingManager';  //接口项目名

var curPopupPanel = null;

var inputAttr = ['name', 'value'];
/*常用方法定义*/
var common = {
    stepsInit: function (obj) {
        var target = $('#' + obj.id);
        var vueElement = '<el-steps :active="active" align-center finish-status="success">' +
            '<el-step v-for="item in stepsList" :title="item.title" :description="item.description"></el-step>' +
            '</el-steps>';
        var html = '<div id="' + obj.id + 'Box">' + vueElement + '</div>';
        $('#' + obj.id).wrap(html); //写入vue dom元素

        var app = new Vue({   //创建vue对象
            el: '#' + obj.id + 'Box',
            data: function () {
                return {
                    active: obj.step || 0,
                    stepsList: obj.stepsList || [],
                }
            },
            methods: {
                next: function () {
                    this.active++;
                    var maxLength = this.stepsList.length;
                    if (this.active > maxLength) this.active = maxLength;
                },
                prev: function () {
                    this.active--;
                    if (this.active < 0) this.active = 0;
                },
                reset: function () {
                    this.active = 0
                },
                get: function (step) {
                    return this.stepsList[step];
                },
                set: function (step) {
                    if (step < 0) step = 0;
                    var maxLength = this.stepsList.length;
                    if (step > maxLength) step = maxLength;
                    this.active = step;
                }
            }
        });
        return app;

    },
    treeSelectInit: function (obj) {
        var target = $('#' + obj.id);
        obj.value = target.attr("value") ? target.attr("value") : (obj.value || "");
        obj.name = target.attr("name") ? target.attr("name") : (obj.name || "");
        var hiddenInput = '<input type="hidden" name="' + obj.name + '" value="' + obj.value + '" id="' + obj.id + '"/>';
        var vueElement = '<el-cascader @change="change" :value="value" ' +
            '                        :options="options"\n' +
            '                        :props="props"\n' +
            '                        separator=">"\n' +
            '                        change-on-select\n' +
            '                        :disabled="isDisabled"\n' +
            '                        :show-all-levels="false"\n' +
            '                ></el-cascader>';
        var html = '<div id="' + obj.id + 'Box">' + hiddenInput + vueElement + '</div>';
        $('#' + obj.id).wrap(html); //写入vue dom元素

        var app = new Vue({   //创建vue对象
            el: '#' + obj.id + 'Box',
            data: function () {
                return {
                    options: [],
                    value: [],
                    isDisabled: obj.disabled || false,
                    props: {
                        label: obj.text,
                        value: obj.field,
                    }
                }
            },
            methods: {
                change: function (res) {
                    var value = '';
                    if (res.length != 0) {
                        console.log(res);
                        value = res[res.length - 1]
                    }
                    $('#' + obj.id).val(value);
                    obj.changeHandler(value);
                },
            }
        });

        common.ajax({
            url: obj.url,
            type: obj.type || 'get',
            data: obj.param || {},
            success: function (res) {
                var options = obj.data ? res[obj.data] : res;

                if (obj.value) {  //获得默认值对应联级数组
                    var ary = [];
                    var selectedItem = {};
                    ary.push(obj.value);
                    options.forEach(function (ele) {
                        if (ele[obj.cId] == obj.value) {
                            selectedItem = ele
                        }
                    })

                    var loop = true;
                    while (loop) {
                        var loop = false;
                        if (!selectedItem.hasOwnProperty(obj.pId)) {
                            return;
                        }
                        options.forEach(function (ele) {
                            if (ele[obj.cId] == selectedItem[obj.pId]) {
                                ary.unshift(ele.id.toString())
                                selectedItem = ele;
                                loop = true;
                            }
                        });
                    }

                    console.log(ary);
                    app.value = ary;

                }


                //转化为树状结构
                app.options = common.listToTree(options, obj.pId, obj.cId);
                //app.value=obj.value.split(',');
            }
        })
    },
    vueSelectInit: function (obj) {
        var target = $('#' + obj.id);
        obj.value = target.attr("value") ? target.attr("value") : (obj.value || "");
        obj.name = target.attr("name") ? target.attr("name") : (obj.name || "");
        var hiddenInput = '<input type="hidden" name="' + obj.name + '" value="' + obj.value + '" id="' + obj.id + '"/>';
        var vueElement = '<el-select v-model="value" clearable :disabled="isDisabled" @change="change" :multiple="isMul?true:false" placeholder="请选择">\n' +
            '    <el-option\n' +
            '            v-for="item in options"\n' +
            '            :key="item.' + obj.field + '"\n' +
            '            :label="item.' + obj.text + '"\n' +
            '            :value="(item.' + obj.field + ').toString()">\n' +
            '    </el-option>\n' +
            '  </el-select>';
        var html = '<div id="' + obj.id + 'Box">' + hiddenInput + vueElement + '</div>';
        $('#' + obj.id).wrap(html); //写入vue dom元素

        var app = new Vue({   //创建vue对象
            el: '#' + obj.id + 'Box',
            data: function () {
                var value = '';
                if (obj.multiple) {
                    if (!obj.value) {
                        value = [];
                    } else {
                        value = obj.value.split(',');
                    }
                } else {
                    value = obj.value;
                }
                return {
                    options: [],
                    value: value,
                    isMul: obj.multiple || false,
                    isDisabled: obj.disabled || false
                }
            },
            methods: {
                change: function (res) {
                    var value = '';
                    if (Array.isArray(res)) {
                        if (res.length != 0) {
                            value = res.join(',')
                        }
                    } else {
                        value = res
                    }

                    $('#' + obj.id).val(value);
                    //console.log($('#'+obj.id).val());
                    obj.changeHandler(res);
                }
            }
        });

        common.ajax({
            url: obj.url,
            type: obj.type || 'get',
            data: obj.param || {},
            success: function (res) {
                app.options = obj.data ? res[obj.data] : res;
            }
        })
    },
    selectInit: function (obj) {
        var target = $('#' + obj.id);
        obj.value = target.attr("value") ? target.attr("value") : (obj.value || "");
        obj.name = target.attr("name") ? target.attr("name") : (obj.name || obj.inputName || "");
        $('#' + obj.id).wrap('<div class="selector">' +
            '<select name="' + (obj.name || "") + '" id="' + obj.id + '" lay-filter="' + obj.id + '" lay-verify="" lay-search ></select>' +
            '</div>');
        common.ajax({
            url: obj.url,
            type: obj.type || 'get',
            data: obj.param || {},
            success: function (res) {
                var form = layui.form;
                var html = '';
                if (obj.data) {
                    res = res[obj.data]
                }

                html += '<option value=""></option>'
                res.forEach(function (ele, index) {
                    if (ele[obj.field] == obj.value) {
                        html += '<option value="' + ele[obj.field || 'id'] + '" selected>' + ele[obj.text] + '</option>'
                    } else {
                        html += '<option value="' + ele[obj.field || 'id'] + '">' + ele[obj.text] + '</option>'
                    }

                });
                $('#' + obj.id || '').html(html)
                form.render('select'); //刷新select选择框渲染
                if (!obj.value) {
                    $('#' + obj.id || '').parent().find('input').val('');
                }
                if (obj.changeHandler) {
                    form.on('select(' + obj.id + ')', function (data) {  //监听选择框事件
                        //console.log(data.elem); //得到select原始DOM对象
                        obj.changeHandler(data.value)
                        //console.log(data.othis); //得到美化后的DOM对象
                    });

                }
            }
        })
    },
    listToTree: function (list, pidName, idName) {
        var ary = [];
        list.forEach(function (ele, index) {
            if (!ele[pidName] || !ele[pidName].toString()) {
                ary.push(mapChildren(ele))
            }
        })

        function mapChildren(parent) {
            parent.children = [];
            list.forEach(function (ele, index) {
                if (ele[pidName] == parent[idName]) {
                    parent.children.push(mapChildren(ele));
                }
            });
            if (parent.children.length == 0) {
                delete parent.children;
            }
            if (parent[idName]) {
                parent[idName] = parent[idName].toString();
            }
            if (parent[pidName]) {
                parent[pidName] = parent[pidName].toString();
            }
            return parent;
        }

        return ary;
    },
    newWindow: function () {
        window.open($('.layui-tab-item.layui-show iframe').attr('src'))
    },
    closePanel: function () {
        layer.close(curPopupPanel);
    },
    tableReload: function (targetId) {
        $('#' + targetId).treegrid('reload');
        $('#' + targetId)
            .treegrid('uncheckAll')
            .treegrid('unselectAll')
            .treegrid('clearSelections');
    },
    popupPanel: function (obj) {
        obj.content = $('#' + obj.contentId);
        obj.type = 1;
        obj.area = 'auto';
        obj.offset = 'auto';
        obj.maxWidth = 'auto';
        obj.maxHeight = 600;
        obj.cancel = function (index, layero) {
            console.log('表单数据reset');
            document.getElementById(obj.contentId).reset();
        }
        curPopupPanel = layer.open(obj);
    },
    unfold: function (targetId) {
        var node = $('#' + targetId).treegrid('getSelected');
        if (node) {
            $('#' + targetId).treegrid('expandAll', node.cid);
        } else {
            $('#' + targetId).treegrid('expandAll');
        }
    },
    fold: function (targetId) {
        var node = $('#' + targetId).treegrid('getSelected');
        if (node) {
            $('#' + targetId).treegrid('collapseAll', node.cid);
        } else {
            $('#' + targetId).treegrid('collapseAll');
        }
    },
    refresh: function (targetId) {
        $('#' + targetId).treegrid('reload');
    },
    menuDomMake: function (ary, dom) {
        var html = '';

        ary.forEach(function (ele) {
            var children = '';
            ele.children.forEach(function (ele2) {
                children += '<dd><a href="javascript:;" data-id="' + ele2.id + '" data-url="' + apiPath + ele2.curl + '">' + ele2.cname + '</a></dd>'
            })

            html += '<li class="layui-nav-item">\n' +
                '                    <a href="javascript:;">' + ele.cname + '</a>\n' +
                '                    <dl class="layui-nav-child">\n' + children +
                '                    </dl>\n' +
                '                </li>'
        });

        dom.append(html);
    },
    menuListFormatter: function (list) {
        var ary = [];
        list.forEach(function (ele) {
            if (ele.clevel == 1) {
                ary.push(addChildren(ele, list));
            }
        })

        function addChildren(obj, list) {
            if (obj) {
                obj.children = [];
                list.forEach(function (ele) {
                    if (ele.ipid == obj.id) {
                        obj.children.push(addChildren(ele, list))
                    }
                })
            }
            return obj;
        }

        return ary;
    },
    /**
     * 使用RSA算法进行加密
     * @param val
     * @returns {*}
     */
    passwordEncrypt: function (val) {
        var modulus = 'b4218a1e629d3feba43da5b18727a49c2dcb0640c4b1c63e7e63c9b6f1897caaa3cb1ac03f948584d3ae5ca9ca5ea56b1cc0471974ffeb105b6fbd15dd4dd804d0c8787723e646a48f1e1814b4d338ca6a7372ecacd374af2cb66288f50e4ee450ee13f17e23eda8f7d658cbd857cc427704022cc3e02e35180a8f45d9a610b1';
        var exponent = '10001';
        var key = RSAUtils.getKeyPair(exponent, '', modulus);
        var pwd = RSAUtils.encryptedString(key, val);
        return pwd;
    },
    /**
     * 使用AES算法进行加密
     * @param word
     * @returns {string}
     */
    passwordEncryptByAes: function (word) {
        //密钥--应和后台java解密或是前台js解密的密钥保持一致（16进制）
        var key = CryptoJS.enc.Utf8.parse("1111wwww2222uuuu");
        //偏移量
        var srcs = CryptoJS.enc.Utf8.parse(word);
        //算法
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
        //替换--防止值为“1”的情况
        var reg = new RegExp('/', "g");
        return encrypted.toString().replace(reg, "#");
    },

    ajax: function (obj) {
        $.ajax({
            url: obj.url,
            type: obj.type,
            data: obj.data || {},
            dataType: obj.dataType,
            success: function (result) {
                if (!result.success) {
                    wToast.error(result.msg)
                    setTimeout(function () {
                        location.replace(apiPath + '/login')
                    }, 2000);
                    return;
                } else {

                }
            },
        });

    }
}

/*封装jquery.toast几个常用的方法*/
var wToast = {
    loading: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'info',
            text: text || '加载中...',
            icon: 'info',
            stack: 1,
            position: 'top-center',
            hideAfter: time || 10000
        })
    },
    warning: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'warning',
            text: text,
            stack: 1,
            icon: 'warning',
            position: 'top-center',
            hideAfter: time || 3000
        })
    },
    error: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'error',
            text: text,
            icon: 'error',
            stack: 1,
            position: 'top-center',
            hideAfter: time || 3000
        })
    },
    info: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'info',
            text: text,
            stack: 1,
            icon: 'info',
            position: 'top-center',
            hideAfter: time || 3000
        })
    },
    success: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'success',
            text: text,
            stack: 1,
            icon: 'success',
            position: 'top-center',
            hideAfter: time || 3000
        })
    },
}

//ajax开始和结束
$(document).ajaxStart(function () {
    console.log('ajax网络连接开始')
}).ajaxStop(function () {
    console.log('ajax网络连接结束');
});

//ajax全局设置
$.ajaxSetup({
    dataFilter: function (data, type) {

        if (data) {

        } else {
            wToast.error('没有返回数据')
        }


        return data;
    },
    timeout: 6000,
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.warn('ajax出错，', XMLHttpRequest, textStatus, errorThrown);
        switch (XMLHttpRequest.status) {
            case 403:
                wToast.error(XMLHttpRequest.status + '，您没有权限进行此操作！');
                break;
            case 404:
                wToast.error(XMLHttpRequest.status + '，请求对象不存在！');
                break;
            case 500:
                wToast.error(XMLHttpRequest.status + '，服务器出错！');
                break;
            case 0:
                wToast.error('连接超时，请检查网络！');
                break;
            default:
                wToast.error(XMLHttpRequest.status + '，' + errorThrown);
                break;
        }
    }
});

function checkAjaxObj(obj) {
    if (!obj) {
        console.error('没有ajax对象');
        return false;
    }
    if (!obj.url) {
        console.error('ajax对象没有指定url');
        return false;
    }
    if (!obj.type) {
        console.error('ajax对象没有指定连接类型');
        return false;
    }

    return true;
}


