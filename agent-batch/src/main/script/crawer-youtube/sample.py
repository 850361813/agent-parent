import re

from localhttp import fetcher

if __name__ == '__main__':
    video_list = []
    soup = fetcher.get_soup_from_html(open('/Users/baidu/text.html'))
    print soup
    video_detail_list = soup.find_all("h3", attrs={'class': 'yt-lockup-title '})
    if video_detail_list is not None:
        for video_detail in video_detail_list:
            pattern = re.compile(
                r'<h3([\s\S]*)data-sessionlink([\s\S]*)href="([\s\S]*)" title="([\s\S]*)</h3>')
            match = pattern.match(video_detail.encode("utf-8"))
            if match is not None:
                print match.group(3)