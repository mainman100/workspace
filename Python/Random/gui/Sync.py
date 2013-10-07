#!/usr/bin/env python

import wx, os, shutil
import threading, Queue
STATUS_TEXT = 0
PROGRESS_BAR = 1
FINISHED = 2
DUMMY = 3
def countFiles(dirPath):
    files = len([f for f in os.listdir(dirPath) if os.path.isfile(os.path.join(dirPath, f))])
    return reduce(lambda x, y:x + countFiles(os.path.join(dirPath, y)), [f for f in os.listdir(dirPath) if os.path.isdir(os.path.join(dirPath, f))], files)


def sync(src, dst, window, root=True):
    if root:
        sync.total = countFiles(src)
        sync.copiedCount = 0
        window.queue.put((PROGRESS_BAR, 0))
        window.queue.put((STATUS_TEXT, ""))
    src_files = [f for f in os.listdir(src) if os.path.isfile(os.path.join(src, f))]
    src_dirs = [f for f in os.listdir(src) if os.path.isdir(os.path.join(src, f))]
    dst_files = [f for f in os.listdir(dst) if os.path.isfile(os.path.join(dst, f))]
    dst_dirs = [f for f in os.listdir(dst) if os.path.isdir(os.path.join(dst, f))]
    for src_file in src_files:
        if src_file not in dst_files or os.path.getmtime(os.path.join(src, src_file)) > os.path.getmtime(os.path.join(dst, src_file)):
            window.queue.put((STATUS_TEXT, "copying {} to {}".format(os.path.join(src, src_file), dst)))
            shutil.copy(os.path.join(src, src_file), dst)
            print "copied {} to {}".format(os.path.join(src, src_file), dst)
            window.queue.put((STATUS_TEXT, ""))
        sync.copiedCount += 1
        window.queue.put((PROGRESS_BAR, 100.0 * sync.copiedCount / sync.total))
        # if 100 * sync.copiedCount / sync.total != 100 * (sync.copiedCount - 1) / sync.total:
        #    print "{}%".format(100 * sync.copiedCount / sync.total)
    for src_dir in src_dirs:
        if src_dir not in dst_dirs:
            os.mkdir(os.path.join(dst, src_dir))
        sync(os.path.join(src, src_dir), os.path.join(dst, src_dir), window, root=False)
    # finished
    if root:
        window.queue.put((STATUS_TEXT, "Finished Copying"))
        window.queue.put((FINISHED, DUMMY))
    
ID_SRC = wx.NewId()
ID_DST = wx.NewId()
class InitPanel(wx.Panel):
    def __init__(self, *args, **kwargs):
        wx.Panel.__init__(self, *args, **kwargs)
        
        # declaring the widgets
        srcButton = wx.Button(self, label="From", id=ID_SRC)
        srcButton.Bind(wx.EVT_BUTTON, self.chooseDir)
        self.srcTxt = wx.TextCtrl(self)
        dstButton = wx.Button(self, label="To", id=ID_DST)
        dstButton.Bind(wx.EVT_BUTTON, self.chooseDir)
        self.dstTxt = wx.TextCtrl(self)
        
        self.progressbar = wx.Gauge(self, range=100)
        self.progressbar.Hide()
        
        
        syncButton = wx.Button(self, label="Copy")
        syncButton.Bind(wx.EVT_BUTTON, self.onSyncClick)
        
        
        self.timer = wx.Timer(self)        
        self.Bind(wx.EVT_TIMER, self.processIncoming, self.timer)
        
        self.queue = Queue.Queue()
        # creating the sizers
        srcSizer = wx.BoxSizer(wx.HORIZONTAL)
        dstSizer = wx.BoxSizer(wx.HORIZONTAL)
        progressSizer = wx.BoxSizer(wx.HORIZONTAL)
        sizer = wx.BoxSizer(wx.VERTICAL)
        # adding widgets
        srcSizer.Add(srcButton, proportion=0, flag=wx.ALL, border=5)
        srcSizer.Add(self.srcTxt, proportion=1, flag=wx.ALL, border=5)
        dstSizer.Add(dstButton, proportion=0, flag=wx.ALL, border=5)
        dstSizer.Add(self.dstTxt, proportion=1, flag=wx.ALL, border=5)
        progressSizer.Add(self.progressbar, proportion=1, flag=wx.ALL, border=5)
        
        sizer.Add(srcSizer, proportion=1, flag=wx.EXPAND | wx.ALL, border=10)
        sizer.Add(dstSizer, proportion=1, flag=wx.EXPAND | wx.ALL, border=10)
        sizer.Add((1, 50))
        sizer.Add(progressSizer, flag=wx.ALL | wx.EXPAND, border=10)
        sizer.Add((1, 50))
        sizer.Add(syncButton, proportion=0, flag=wx.ALL, border=15)
        
        self.SetSizer(sizer)
        self.Layout()
    def chooseDir(self, e):
        """ Open a file"""
        dlg = wx.DirDialog(self, "Choose a directory:",
                           style=wx.DD_DEFAULT_STYLE)
        if dlg.ShowModal() == wx.ID_OK:
            if e.GetId() == ID_SRC:
                self.srcTxt.SetValue(dlg.GetPath())
            else :
                self.dstTxt.SetValue(dlg.GetPath())
                
        dlg.Destroy()
    def onSyncClick(self, e):
        if self.srcTxt.GetValue() == '' or self.dstTxt.GetValue() == '':
            return
        self.progressbar.Show()
        self.Layout()
        thread = threading.Thread(target=sync, args=(self.srcTxt.GetValue(), self.dstTxt.GetValue()), kwargs={"window":self})
        thread.start()
        self.timer.Start(100)
    def processIncoming(self, e):
        while self.queue.qsize():
            try:
                msg = self.queue.get(False)  # don't block to wait for queues
                if msg[0] == STATUS_TEXT:
                    self.GetParent().SetStatusText(msg[1])
                elif msg[0] == PROGRESS_BAR:
                    self.progressbar.SetValue(msg[1])        
                elif msg[0] == FINISHED:
                    self.timer.Stop()    
            except Queue.Empty:
                print "Empty Queue"


class MainWindow(wx.Frame):
    def __init__(self, parent, title):

        # A "-1" in the size parameter instructs wxWidgets to use the default size.
        # In this case, we select 200px width and the default height.
        wx.Frame.__init__(self, parent, title=title, size=(500, 300))
        self.CreateStatusBar()  # A Statusbar in the bottom of the window
        initPanel = InitPanel(self)
        # Use some sizers to see layout options
        sizer = wx.BoxSizer(wx.VERTICAL)
        sizer.Add(initPanel, flag=wx.EXPAND)
        self.SetSizer(sizer)
        self.Layout()
        self.Show()


app = wx.App(False)
frame = MainWindow(None, "Copying")
app.MainLoop()
