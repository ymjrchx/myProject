"""
使用tkinter创建GUI
- 在窗口上制作动画
Version: 0.1
Author: 骆昊
Date: 2018-03-14
"""
import tkinter
import time

def play_animation():
    canvas.move(oval,2,2)
    canvas.update()
    top.after(50,play_animation)
x=10
y=10
top=tkinter.Tk()
top.geometry('600x600')
top.title("动画效果")
top.resizable(False,False)
top.wm_attributes('-topmost',1)
canvas=tkinter.Canvas(top,width=600,height=600,bd=0,highlightthickness=0)
canvas.create_rectangle(0,0,600,60,fill='gray')
oval=canvas.create_oval(10,10,60,60,fill='red')
canvas.pack()
top.update()
play_animation()
tkinter.mainloop()
if __name__ == '__main__':
    pass
