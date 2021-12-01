/*
 * Denna annotering (~markör) används av testfallen för att
 * hitta vad som ska testas i vissa av uppgifterna. Instruktioner
 * för hur den används finns i instruktionerna för U6.3. 
 * 
 * Denna fil ska aldrig lämnas in i ilearn, den läggs automatiskt
 * till i VPL. 
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UnderTest {
	String id();
}
