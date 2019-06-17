//准备视图对象

console.log(list);

window.viewObj = {
    tbData: JSON.parse(list),
    typeData: [
        {tid: '00', name: '按钮'},
        {tid: '01', name: '链接'},
        {tid: '02', name: '图片'},
        {tid: '03', name: '文字'},
        {tid: '04', name: '其它'}
    ],
    renderSelectOptions: function (data, settings) {
        settings = settings || {};
        var valueField = settings.valueField || 'value',
            textField = settings.textField || 'text',
            selectedValue = settings.selectedValue || "";
        var html = [];
        for (var i = 0, item; i < data.length; i++) {
            item = data[i];
            html.push('<option value="');
            html.push(item[valueField]);
            html.push('"');
            if (selectedValue && item[valueField] == selectedValue) {
                html.push(' selected="selected"');
            }
            html.push('>');
            html.push(item[textField]);
            html.push('</option>');
        }
        return html.join('');
    }
};

//layui 模块化引用
layui.use(['jquery', 'table', 'layer'], function () {
    var $ = layui.$, table = layui.table, form = layui.form, layer = layui.layer;

    //数据表格实例化
    var tbWidth = $("#tableRes").width();
    var layTableId = "layTable";
    var tableIns = table.render({
        elem: '#dataTable',
        id: layTableId,
        data: viewObj.tbData,
        width: tbWidth,
        page: false,
        loading: true,
        even: false, //不开启隔行背景
        cols: [[
            {title: '序号', type: 'numbers'},
            {
                field: 'ctype', title: '资源类型', templet: function (d) {
                    var options = viewObj.renderSelectOptions(viewObj.typeData, {
                        valueField: "tid",
                        textField: "name",
                        selectedValue: d.ctype
                    });
                    return '<a lay-event="type"></a><select name="ctype" lay-filter="type"><option value="">请选择分类</option>' + options + '</select>';
                }
            },
            {field: 'cname', title: '名称', edit: 'text'},
            {field: 'ckey', title: '关键字', edit: 'text'},
            {field: 'corder', title: '排序', edit: 'text'},

            {
                field: 'id', title: '操作', templet: function (d) {
                    if (d.id !== null) {
                        return '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete" lay-id="' + d.id + '"><i class="layui-icon layui-icon-delete"></i>删除</a>';
                    } else {
                        return '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i>移除</a>';
                    }

                }
            }
        ]],
        done: function (res, curr, count) {
            viewObj.tbData = res.data;
        }
    });

    //定义事件集合
    var active = {
        addRow: function () {	//添加一行
            var oldData = table.cache[layTableId];
            console.log(oldData);
            var newRow = {id: null, ctype: '00', cname: '请填写名称', ckey: '请填写关键字', corder: '请填写排序'};
            oldData.push(newRow);
            tableIns.reload({
                data: oldData
            });
        },
        updateRow: function (obj) {
            var oldData = table.cache[layTableId];
            console.log(oldData);
            for (var i = 0, row; i < oldData.length; i++) {
                row = oldData[i];
                if (row.id == obj.id) {
                    $.extend(oldData[i], obj);
                    return;
                }
            }
            tableIns.reload({
                data: oldData
            });
        },
        removeEmptyTableCache: function () {
            var oldData = table.cache[layTableId];
            for (var i = 0, row; i < oldData.length; i++) {
                row = oldData[i];
                if (!row || !row.id) {
                    oldData.splice(i, 1);    //删除一项
                }
                continue;
            }
            tableIns.reload({
                data: oldData
            });
        },
        save: function () {
            var oldData = table.cache[layTableId];
            console.log(oldData);
            for (var i = 0, row; i < oldData.length; i++) {
                row = oldData[i];
                if (!row.ctype) {
                    layer.msg("检查每一行，请选择分类！", {icon: 5});
                    return;
                }
                if (!row.cname){
                    layer.msg("检查每一行，请填写名称！", {icon: 5});
                    return;
                }
                if (!row.ckey){
                    layer.msg("检查每一行，请填写关键字！", {icon: 5});
                    return;
                }
                if (!row.corder){
                    layer.msg("检查每一行，请填写排序！", {icon: 5});
                    return;
                }
            }
            console.log(oldData);
            $.ajax({
                type: 'POST',
                url: ctx + '/moduleSource/moduleSourceSave/'+$('#moduleId').val(),
                data: JSON.stringify(oldData),
                contentType: "application/json;charset=utf-8",
                dataType:'json',
                success: function (res) {
                    if (res.status == 200) {
                        layer.msg(res.msg, {icon: 6});
                        tableIns.reload({data: res.data});
                    }
                    else {
                        layer.msg(res.msg, {icon: 5});
                        tableIns.reload({data: oldData});
                    }
                }
            });
            //document.getElementById("jsonResult").innerHTML = JSON.stringify(table.cache[layTableId], null, 2);	//使用JSON.stringify() 格式化输出JSON字符串
        }
    }

    //激活事件
    var activeByType = function (type, arg) {
        if (arguments.length === 2) {
            active[type] ? active[type].call(this, arg) : '';
        } else {
            active[type] ? active[type].call(this) : '';
        }
    }

    //注册按钮事件
    $('.layui-btn[data-type]').on('click', function () {
        var type = $(this).data('type');
        activeByType(type);
    });

    //监听select下拉选中事件
    form.on('select(type)', function (data) {
        var elem = data.elem; //得到select原始DOM对象
        $(elem).prev("a[lay-event='type']").trigger("click");
    });

    //监听工具条
    table.on('tool(dataTable)', function (obj) {
        var data = obj.data, event = obj.event, tr = obj.tr; //获得当前行 tr 的DOM对象;
        console.log(data);
        switch (event) {
            case "type":
                //console.log(data);
                var select = tr.find("select[name='ctype']");
                if (select) {
                    var selectedVal = select.val();
                    if (!selectedVal) {
                        layer.tips("请选择一个分类", select.next('.layui-form-select'), {tips: [3, '#FF5722']}); //吸附提示
                    }
                    console.log(selectedVal);
                    $.extend(obj.data, {'ctype': selectedVal});
                    activeByType('updateRow', obj.data);	//更新行记录对象
                }
                break;
            case "del":
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                activeByType('removeEmptyTableCache');
                break;
            case "delete":
                layer.confirm('真的删除行么？', function (index) {
                    console.log(data.id);
                    $.ajax({
                        url: ctx + '/moduleSource/moduleSourceDel',
                        type: 'post',
                        data: {"moduleSourceId": data.id, "id": $('#moduleId').val()},
                        dataType: "json",
                        success: function (res) {
                            if (res.status == 200) {
                                console.log(res);
                                obj.del();
                                layer.close(index);
                                layer.msg(res.msg, {icon: 6});
                                tableIns.reload({data: res.data});
                            }
                            else {
                                layer.msg(res.msg, {icon: 5});
                            }
                        }
                    });
                });
                break;
        }
    });
});