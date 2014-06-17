import xlrd 
workbook = xlrd.open_workbook('fedex_locations.xls')
worksheet = workbook.sheet_by_name('Export Worksheet')
num_rows = worksheet.nrows - 1
num_cells = worksheet.ncols - 1
curr_row = -1
map={}
while curr_row < num_rows:
    curr_row += 1
    row = worksheet.row(curr_row)        
    curr_cell = -1
    print '{'
    while curr_cell < num_cells-1:
        curr_cell += 1
        coln=',' if not curr_cell==num_cells else ''               
        cell_value = worksheet.cell_value(curr_row, curr_cell)
        if curr_row==0:
            map[curr_cell]=cell_value
            print curr_cell,cell_value
        else:
            print map[curr_cell],':','"'+ str(cell_value)+'"',coln
        
    print '},'
