package D5_access_modifier2;

import D5_access_modifier.Demo;

public class DemoInAnotherPackage {
    /**
     * Only able to access public fields
     * @return
     */
    int foo() {
        Demo demo = new Demo();
        return demo.publicField;
    }
}
