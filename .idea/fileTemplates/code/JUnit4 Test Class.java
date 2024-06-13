#parse("Copyright.java")

#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import static org.assertj.core.api.Assertions.*;

/**
 * Test for ${CLASS_NAME}
 * 
 * @author ${USER}
 */
public class ${NAME} {
  ${BODY}
}