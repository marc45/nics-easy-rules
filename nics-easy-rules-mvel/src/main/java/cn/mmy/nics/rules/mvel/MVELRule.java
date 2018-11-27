/**
 * The MIT License
 *
 *  Copyright (c) 2018, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package cn.mmy.nics.rules.mvel;

import cn.mmy.nics.rules.api.Action;
import cn.mmy.nics.rules.api.Condition;
import cn.mmy.nics.rules.api.Facts;
import cn.mmy.nics.rules.api.Rule;
import cn.mmy.nics.rules.core.BasicRule;
import cn.mmy.nics.rules.api.Action;
import cn.mmy.nics.rules.api.Condition;
import cn.mmy.nics.rules.api.Facts;
import cn.mmy.nics.rules.api.Rule;
import cn.mmy.nics.rules.core.BasicRule;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Rule} implementation that uses <a href="https://github.com/mvel/mvel">MVEL</a> to evaluate and execute the rule.
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public class MVELRule extends BasicRule {

    private Condition condition = Condition.FALSE;
    private List<Action> actions = new ArrayList<>();

    /**
     * Create a new MVEL rule.
     */
    public MVELRule() {
        super(Rule.DEFAULT_NAME, Rule.DEFAULT_DESCRIPTION, Rule.DEFAULT_PRIORITY);
    }

    /**
     * Set rule name.
     *
     * @param name of the rule
     * @return this rule
     */
    public MVELRule name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set rule description.
     *
     * @param description of the rule
     * @return this rule
     */
    public MVELRule description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Set rule priority.
     *
     * @param priority of the rule
     * @return this rule
     */
    public MVELRule priority(int priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Specify the rule's condition as MVEL expression.
     * @param condition of the rule
     * @return this rule
     */
    public MVELRule when(String condition) {
        this.condition = new MVELCondition(condition);
        return this;
    }

    /**
     * Add an action specified as an MVEL expression to the rule.
     * @param action to add to the rule
     * @return this rule
     */
    public MVELRule then(String action) {
        this.actions.add(new MVELAction(action));
        return this;
    }

    @Override
    public boolean evaluate(Facts facts) {
        return condition.evaluate(facts);
    }

    @Override
    public void execute(Facts facts) throws Exception {
        for (Action action : actions) {
            action.execute(facts);
        }
    }
}
