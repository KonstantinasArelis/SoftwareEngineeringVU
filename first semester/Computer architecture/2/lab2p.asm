org 100h


%include 'yasmmac.inc'

section .text
	
	start:

	macPutString 'Konstantinas Arelis, 1 kursas, 3gr', crlf, '$'
	macNewLine

	.komandineEilute:

	mov si, 0h
    mov bx, 82h
    jmp .patikrinti

	.patikrinti:

    mov cl, [bx+si]
    cmp cl, 20h
    jl .pridetiNuli

    inc si
    jmp .patikrinti
        
    ; prie galo prideti 0
    .pridetiNuli:
    mov byte [bx+si], 0h
    xor si, si

	macPutString 'Iveskite isvesties faila: ', crlf, '$'

	; Gaunamas isvesties failo pav
	mov al, 128
	mov dx, outputFile
	call procGetStr

	macNewLine	
	
	; atidaromas ivesties failas
	mov dx, bx
	call procFOpenForReading
	jnc .atidarytIsvestiesFaila
	macPutString 'Klaida atidarant ivesites faila', '$'

	exit


	; Open output file
	.atidarytIsvestiesFaila:
		mov     [IvestiesDeskriptorius], bx
		mov     dx, outputFile ; cia bus rasoma
		call    procFCreateOrTruncate	       
                  
 		jnc     .IsvestiesFailasAtidarytas
		macPutString 'Klaida atidarant isvesties faila', '$'
		jmp     .klaida

	.IsvestiesFailasAtidarytas:
		mov     [isvestiesDeskriptorius], bx

	jmp .kitaEilute
	.failasNesibaige:
		call    procGetLine

		mov di, ivestiesBufferis                                      
		.kolNeKabliataskis:
		mov     al, [di] ; po simboli iki kabliataskio
		inc     di

		cmp     al, ';'                                          
		jne     .kolNeKabliataskis ; Jeigu ne lygu

		
		.antrasStulpelis
		mov al, [di]
		inc di

		cmp al, 'U'
		je .kitaEilute
		cmp al, 'V'

		je .kitaEilute
		cmp al, byte ';'
		jne .antrasStulpelis

		call procskcSuma

		cmp [skcSuma], word 7
		jne .kitaEilute

		mov [ketvirtasTinka],byte 0
		mov     al, [di]                                        
		
		cmp     al, '0'
		jne     .neNulis
		cmp     byte [di-1], ';'
		jne     .neNulis
		cmp     byte [di+1], ';'
		jne     .neNulis
		mov [ketvirtasTinka],byte 1
		.neNulis:		
		cmp [ketvirtasTinka],byte 1
		jne .kitaEilute

		.kolNeKabliataskis1:
		mov     al, [di]                                        
		inc     di

		cmp     al, ';'                                          
		jne     .kolNeKabliataskis1
		
		.kolNeKabliataskis2:
		mov     al, [di]
		inc     di

		cmp     al, ';'                     
		jne     .kolNeKabliataskis2

		call procBelongsToInterval
		cmp  [priklausoIntervalui], byte 0
		je   .kitaEilute

		mov bx, [isvestiesDeskriptorius]
		mov cx, [nuskaitytuBaitu]
		mov dx, ivestiesBufferis

		mov ah, 40h
		int 21h

		.kitaEilute:
		cmp [paskutineEil], byte 0
		je .failasNesibaige

	.end
		mov bx, [isvestiesDeskriptorius]
		call procFClose

	.klaida:

		mov bx, [IvestiesDeskriptorius]
		call procFClose

	exit

%include 'yasmlib.asm'

procGetLine:


	push ax
	push bx
	push cx
	push si


	mov bx, [IvestiesDeskriptorius]


	mov si, 0

	.loop
	call   procFGetChar

	cmp    ax, 0
	je     .failoPabaiga
	jc     .failoPabaiga

	mov    [ivestiesBufferis+si], cl
	inc    si

	cmp    cl, 0Ah

	je     .eilutesPabaiga
	jmp    .loop

	.failoPabaiga:
	mov    [paskutineEil], byte 1


	.eilutesPabaiga:
	mov    [ivestiesBufferis+si], byte '$'
	mov    [nuskaitytuBaitu], si

	pop si
	pop cx

	pop bx
	pop ax

	ret

; in (di), out (di)
procskcSuma

    push ax
    push cx

    xor  cx, cx
    xor  ax, ax

.loop:
    add  cx, ax

.minus:
    mov  al, [di]
    inc  di
    sub  al, '0'

    jb   .minus          ; jeigu neigiamas
    cmp  al, 9
    jbe  .loop
.above:
    mov  [skcSuma], cx
    pop  cx
    pop  ax ; di rodys i kita stulpeli
    ret



procBelongsToInterval
	push ax
	xor ax, ax
.pirmas:
	mov al, [di]
	inc di
	cmp al, '-' ;neigiamas netinka
	je  .break

	cmp al, '2'
	je  .pirmoRiba

	cmp al, '3'
	je  .priklauso

	cmp al, '4'
	je  .antraRiba

	jmp .break

.pirmoRiba:

	mov al, [di]
	inc di
	cmp al, '.'
	je  .pirmoRiba

	cmp al, '3'
	jb  .break
	ja  .priklauso
	mov al, [di]
	cmp al, '4'

	jae .priklauso
	jb  .break

.antraRiba:
	mov al, [di]
	inc di
	cmp al, '.'

	je  .antraRiba
	cmp al, '5'
	ja  .break
	jb  .priklauso

	mov al, [di]
	cmp al, '0'
	je  .priklauso
	ja  .break

.priklauso:
	mov [priklausoIntervalui], byte 1 ;boolean
	jmp .end

.break:
	mov [priklausoIntervalui], byte 0
	jmp .end

.end:
	pop ax
	ret

section .data
	
	ivestiesBufferis:
		db 64
		times 66 db '$'

	inputFile:
		times 255 db 00      ; ASCIIZ

	skcSuma:
		dw 0

	IvestiesDeskriptorius:
		dw 0000		

	
	isvestiesDeskriptorius:
		dw 0000	


	priklausoIntervalui:
		db 00
	
	ketvirtasTinka:
		db 00

	nuskaitytuBaitu:
		dw 0

	outputFile:
		times 255 db 00

	paskutineEil:
		db 00

section .bss 			; uninitialized data
