#parse("Copyright.java")

#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import static org.assertj.core.api.Assertions.*;

#parse("File Header.java")
class ${NAME} {
  ${BODY}
}