function Pager(tableName, itemsPerPage) {
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;
    
    this.showRecords = function(from, to) {        
        var rows = document.getElementById(tableName).rows;
        // i starts from 1 to skip table header row
        for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to)  
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
    }
    
    this.showPage = function(pageNumber) {
        if (! this.inited) {
            alert("Error 1 !");
            return;
        }

        var oldPageAnchor = document.getElementById('pg'+this.currentPage);
        oldPageAnchor.className = 'pg-normal';
        oldPageAnchor.style.color = '#8B8989';
        
        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg'+this.currentPage);
        newPageAnchor.className = 'pg-selected';
        newPageAnchor.style.color = '#B72E27';
        
        
        var from = (pageNumber - 1) * itemsPerPage + 1;
        var to = from + itemsPerPage - 1;
        this.showRecords(from, to);
    }   
    
    this.prev = function() {
        if (this.currentPage > 1)
            this.showPage(this.currentPage - 1);
    }
    this.prevone = function() {
        if (this.currentPage > 1)
            this.showPage(1);
    }

    this.range = function (){
        if (this.pages >= 8 ){

    }
    }
    
    this.next = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }                        
    this.nextone = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.pages);
        }
    }
    
    this.init = function() {
        var rows = document.getElementById(tableName).rows;
        var records = (rows.length - 1); 
        this.pages = Math.ceil(records / itemsPerPage);
        this.inited = true;
    }

    this.showPageNav = function(pagerName, positionId) {
        if (! this.inited) {
            alert("Error 2 !");
            return;
        }
        var element = document.getElementById(positionId);    	
        var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal" style="size:12px;" onmouseover="this.style.cursor=\'pointer\'"> &#171 Anterior </span> | ';
        for (var page = 1; page <= this.pages; page++)
            pagerHtml += '<span style="display: inline-table;font-weight: bold;color:#8B8989" id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');"  style="size:12px;" onmouseover="this.style.cursor=\'pointer\'">' + page + '</span> - ';
        pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal" style="size:12px;" onmouseover="this.style.cursor=\'pointer\'">  | Siguiente &#187;</span>';
        element.innerHTML = pagerHtml;
    }
}


