<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Demo</title>
    <link rel="stylesheet" href="/static/css/bootstrap-table.min.css">
    <script type="text/javascript" src="/static/js/jquery-3.2.1.min.js"/>
    <script type="text/javascript" src="/static/js/bootstrap-table.min.js"></script>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
</head>
<body >
<div class="page-container">
    <div style="height:500px">
        <table class="table table-bordered table-striped" id="usertable">
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        $("#usertable").bootstrapTable({
            method:'get',
            url:"${ctx}/user/page",
            cache: false,
            striped: true,
            pagination: true,
            pageList: [10,20,50],
            pageSize:10,
            pageNumber:1,
            queryParams: function(params){
                paramsReturn = {
                    limit: params.limit,
                    offset: params.offset,
                };
                return paramsReturn;
            },
            sidePagination:'server',
            contentType: "application/x-www-form-urlencoded",
            showColumns: true,
            showRefresh: true,
            smartDisplay:true,
            columns: [
                {
                    field: 'id',
                    title: '编号',
                    align: 'center',
                    width: '60',
                    valign: 'middle',
                },
                {
                    field: 'username',
                    title: '名称',
                    align: 'center',
                    width: '60',
                    valign: 'middle',
                },
                {
                    field: 'age',
                    title: '年龄',
                    align: 'center',
                    width: '40',
                    valign: 'middle',
                },
                {
                    field: 'password',
                    title: '密码',
                    align: 'center',
                    width: '120',
                    valign: 'middle',
                },
                {
                    field: 'mail',
                    title: '邮箱',
                    align: 'center',
                    width: '40',
                    valign: 'middle',
                },
                {
                    title: '用户操作',
                    align: 'center',
                    width: '240',
                    valign: 'middle',
                    formatter: function(value,row,index){
                        var str = "";
                        if(row.type == 0)
                            str += '<button class="btn btn-secondary btn-xs"><i class="fa fa-edit"></i><span>查看</span></button>';
                        else
                            str += '<button class="btn btn-warning btn-xs"><i class="fa fa-edit"></i><span >编辑</span></button>';
                        return str;
                    }
                },
            ]
        });
    });
</script>
</html>