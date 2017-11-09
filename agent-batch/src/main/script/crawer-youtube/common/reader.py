
def read(path):
    file_data = []
    f = open(path)
    line = f.readline()
    while line:
        file_data.append(line)
        line = f.readline()
    f.close()
    return file_data
