import cv2

image = cv2.imread("letrasEnCeleste.png")
nombre = ['!', '"', '#','$','%','&',"'",'(',')','*','+',',','-','.','div','0','1','2','3','4','5','6','7','8','9',':',';','<','=','>','?','@','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','[','/',']']
for i in range(61):
  crop = image[0:9 , (8*i):(8*(i+1))]
  cv2.imwrite(nombre[i]+'.png',crop)
exit
