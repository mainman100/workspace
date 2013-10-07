import os, sys
import shutil

def countFiles(dirPath):
    files = len([f for f in os.listdir(dirPath) if os.path.isfile(os.path.join(dirPath, f))])
    return reduce(lambda x, y:x + countFiles(os.path.join(dirPath, y)), [f for f in os.listdir(dirPath) if os.path.isdir(os.path.join(dirPath, f))], files)

def sync(src, dst, root=True):
    if root:
        sync.total = countFiles(src)
        sync.copiedCount = 0
        if not os.path.exists(dst):
            os.mkdir(dst)
    src_files = [f for f in os.listdir(src) if os.path.isfile(os.path.join(src, f))]
    src_dirs = [f for f in os.listdir(src) if os.path.isdir(os.path.join(src, f))]
    dst_files = [f for f in os.listdir(dst) if os.path.isfile(os.path.join(dst, f))]
    dst_dirs = [f for f in os.listdir(dst) if os.path.isdir(os.path.join(dst, f))]
    for src_file in src_files:
        # file doesn't exit or out dated
        if src_file not in dst_files or os.path.getmtime(os.path.join(src, src_file)) > os.path.getmtime(os.path.join(dst, src_file)):            
            shutil.copy(os.path.join(src, src_file), dst)
            print "copied {} to {}".format(src_file, dst)
        sync.copiedCount += 1
        if 100 * sync.copiedCount / sync.total != 100 * (sync.copiedCount - 1) / sync.total:
            print "{}%".format(100 * sync.copiedCount / sync.total)
    for src_dir in src_dirs:
        if src_dir not in dst_dirs:
            os.mkdir(os.path.join(dst, src_dir))
        sync(os.path.join(src, src_dir), os.path.join(dst, src_dir), False)
    
def syncDropbox():
    workspace = "/home/loai/workspace"
    srcs = os.listdir(workspace)
    dropbox = "/home/loai/Dropbox/workspace"
    for src in srcs:
        if src != ".metadata":
            sync(os.path.join(workspace, src), os.path.join(dropbox, src))
    # other dirs



def numberFiles(path, deep=False):
    """
    Renames files in a directory sequentially
    
    @param path: the path to the parent directory
    @param deep: whether to rename all sub directories recursively
    """
    all_ = os.listdir(path)
    files = [f for f in all_ if os.path.isfile(os.path.join(path, f))]
    dirs = [f for f in all_ if os.path.isdir(os.path.join(path, f))]
    # a short hand
    def base(f):
        return os.path.splitext(f)[0]
    # create a set that contains taken numbers
    numbered_files = {base(f) for f in files if base(f).isdigit() and int(base(f)) <= len(files)}
    count = 1
    for file_ in files:
        # check that the file number is not used
        if base(file_) not in numbered_files:
            # loop on the set until finding a non used number
            while str(count) in numbered_files:
                count += 1
            new_name = str(count) + os.path.splitext(file_)[1]
            os.rename(os.path.join(path, file_), os.path.join(path, new_name))
            count += 1
    if deep:
        map(lambda x:numberFiles(os.path.join(path, x)), dirs)
    
if __name__ == '__main__':
    numberFiles("/home/loai/Desktop/number")
    
