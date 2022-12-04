//    import {str} from './Top10MostViewedBookesInACertainPeriod.jsp';
//import {sec} from './Top10MostViewedBookesInACertainPeriod.jsp';
function generate() {
//<script type="text/javascript" src="Top10MostViewedBookesInACertainPeriod.jsp"></script>
    
    
    var doc = new jsPDF('p', 'pt', 'letter');
    var htmlstring = '';
    var tempVarToCheckPageHeight = 0;
    var pageHeight = 0;
    pageHeight = doc.internal.pageSize.height;
    specialElementHandlers = {
        // element with id of "bypass" - jQuery style selector  
        '#bypassme': function (element, renderer) {
            // true = "handled elsewhere, bypass text extraction"  
            return true
        }
    };
    margins = {
        top: 150,
        bottom: 60,
        left: 40,
        right: 40,
        width: 600
    };
    var y = 20;
    doc.setLineWidth(2);
    
    doc.text(200, y = y + 30, "Readers Are innovators");//Readers Are innovators
    doc.autoTable({
        html: '#simple_table',
        startY: 70,
        theme: 'grid',
        columnStyles: {
            0: {
                cellWidth: 180,
            },
            1: {
                cellWidth: 180,
            },
            2: {
                cellWidth: 180,
            }
        },
        styles: {
            minCellHeight: 40
        }
    })
    doc.save('Readers Are Innovators Report.pdf');
}